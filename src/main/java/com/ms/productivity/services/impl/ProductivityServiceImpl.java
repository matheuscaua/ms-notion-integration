package com.ms.productivity.services.impl;

import com.ms.productivity.clients.NotionClient;
import com.ms.productivity.dtos.notion.NotionDatabaseDTO;
import com.ms.productivity.dtos.notion.NotionItemDTO;
import com.ms.productivity.dtos.productivity.ProductivityResponseDTO;
import com.ms.productivity.enums.NotionItemPriorityEnum;
import com.ms.productivity.enums.ParameterDescriptionEnum;
import com.ms.productivity.models.Parameter;
import com.ms.productivity.models.Productivity;
import com.ms.productivity.repositories.ProductivityRepository;
import com.ms.productivity.services.ParameterService;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductivityServiceImpl {

    @Autowired
    private ProductivityRepository repository;
    @Autowired
    private NotionClient notionClient;
    @Autowired
    private ParameterService parameterService;

    public ProductivityResponseDTO calculate(){
        Map<String, Integer> valueProductivity;
        Productivity productivity = new Productivity();
        NotionDatabaseDTO notionDatabase = findNotionDatabase();

        if(notionDatabase != null) {
            List<NotionItemDTO> allItems = notionDatabase.getItems();
            List<NotionItemDTO> completedItems = notionItemsCompleted(notionDatabase);
            valueProductivity = calculateProductivity(completedItems, allItems);

            productivity = createProductivityModel(productivity,
                    valueProductivity, completedItems.size(), allItems.size());

            save(productivity);
            return successProductivityResponseDTO();
            //Enviar informações para criar gráfico de produtividade //Criar microserviço...
        }return errorProductivityResponseDTO();
    }
    public NotionDatabaseDTO findNotionDatabase(){
        Parameter urlBaseNotion = findBaseUrlNotion();
        Parameter headersNotion = findHeaderNotion();
        Map<String, String> headers = parameterService.extractNotionHeaders(headersNotion);
        if(headers != null && !headers.isEmpty()) {
            try{
                return notionClient.getNotionDatabase(urlBaseNotion.getValue(), headers);
            }catch (HttpStatusCodeException e){
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    public Productivity createProductivityModel(Productivity productivity,
                                                Map<String, Integer> points,
                                                Integer completedItems,
                                                Integer totalItems) {
        productivity.setProductivity(points.get("Completed Items"));
        productivity.setTotal(points.get("Total Items"));
        productivity.setCompletedItems(completedItems);
        productivity.setTotalItems(totalItems);
        productivity.setSaveDate(LocalDateTime.now());
        return productivity;
    }


    public List<NotionItemDTO> notionItemsCompleted(NotionDatabaseDTO notionDatabase){
        return notionDatabase.getItems().parallelStream().filter(i ->
                i.getProperties().getFeito().getCheckbox().equals(true)).collect(Collectors.toList());
    }
    public Map<String, Integer> calculateProductivity(List<NotionItemDTO> notionItemsCompleted, List<NotionItemDTO> notionItems) {
        Map<String, Integer> points = new HashMap<>();
        var priorityItemsCompleted = extractPriorityItems(notionItemsCompleted);
        var priorityTotalItems = extractPriorityItems(notionItems);
        var pointsTotalItems = extractPointsItems(priorityTotalItems);
        var pointsCompletedItems = extractPointsItems(priorityItemsCompleted);

        points.put("Total Items", pointsTotalItems);
        points.put("Completed Items", pointsCompletedItems);

        var i = (pointsCompletedItems * 100) / pointsTotalItems;

        return points;
    }
    public Integer extractPointsItems(List<Integer> priorityItems){
        return  (priorityItems.get(0) * NotionItemPriorityEnum.URGENTE.getWeightEnum())
                + (priorityItems.get(1) * NotionItemPriorityEnum.IMPORTANTE.getWeightEnum())
                + (priorityItems.get(2) * NotionItemPriorityEnum.SEM_PRESSA.getWeightEnum());
    }

    public List<Integer> extractPriorityItems(List<NotionItemDTO> notionItemsCompleted){
        int qtdUrgentItems = 0;
        int qtdImportantItems = 0;
        int qtdUnhurriedItems = 0;

        for (NotionItemDTO item: notionItemsCompleted) {
            String priority = item.getProperties().getPrioridade().getSelect().getName();
            if(StringUtils.isBlank(priority)) log.error("ITEM ERROR - The item dont priority value! ");
            else {
                if (priority.toUpperCase().equals(NotionItemPriorityEnum.URGENTE.toString())) qtdUrgentItems += 1;
                if (priority.toUpperCase().equals(NotionItemPriorityEnum.IMPORTANTE.toString())) qtdImportantItems += 1;
                if (priority.toUpperCase().equals(NotionItemPriorityEnum.SEM_PRESSA.toString())) qtdUnhurriedItems += 1;
            }
        }
        return Arrays.asList(qtdUrgentItems,qtdImportantItems,qtdUnhurriedItems);
    }
    public ProductivityResponseDTO successProductivityResponseDTO(){
        var productivityResponse = new ProductivityResponseDTO();
        productivityResponse.setCode(200);
        productivityResponse.setHttpStatus(HttpStatus.OK);
        return productivityResponse;
    }

    public ProductivityResponseDTO errorProductivityResponseDTO(){
        var productivityResponse = new ProductivityResponseDTO();
        productivityResponse.setCode(404);
        productivityResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        return productivityResponse;
    }
    public Parameter findBaseUrlNotion(){
        return parameterService.findParameterByDescription(ParameterDescriptionEnum.URL_BASE_NOTION.toString());
    }
    public Parameter findHeaderNotion(){
        return parameterService.findParameterByDescription(ParameterDescriptionEnum.HEADERS_NOTION.toString());
    }
    public void save(Productivity productivity){
        repository.save(productivity);
    }
}
