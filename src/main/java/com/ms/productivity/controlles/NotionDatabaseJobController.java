package com.ms.productivity.controlles;

import com.ms.productivity.clients.NotionClient;
import com.ms.productivity.dtos.NotionDatabaseDTO;
import com.ms.productivity.dtos.NotionItemDTO;
import com.ms.productivity.enums.NotionItemPriorityEnum;
import com.ms.productivity.enums.ParameterDescriptionEnum;
import com.ms.productivity.models.Parameter;
import com.ms.productivity.models.Productivity;
import com.ms.productivity.services.ParameterService;
import com.ms.productivity.services.impl.ProductivityServiceImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class NotionDatabaseJobController {

    @Autowired
    private NotionClient notionClient;

    @Autowired
    private ParameterService parameterService;

    @Autowired
    private ProductivityServiceImpl productivityService;
     /* *
     * Essa classe realiza o cálculo para a produtividade
     * */
    public void execute(){
        Map<String, Integer> valueProductivity;
        Productivity productivity;
        NotionDatabaseDTO notionDatabase = findNotionDatabase();
        List<NotionItemDTO> items = notionDatabase.getItems();
        if(notionDatabase != null) {
            List<NotionItemDTO> completedItems = notionItemsCompleted(notionDatabase);
            valueProductivity = calculateProductivity(completedItems, items);
            productivity = productivityService.createProductivityModel(valueProductivity, completedItems.size(), items.size());
            productivityService.save(productivity);
        }
    }

    public NotionDatabaseDTO findNotionDatabase(){
         Parameter urlBaseNotion = findBaseUrlNotion();
         Parameter headersNotion = findHeaderNotion();
         Map<String, String> headers = parameterService.extractNotionHeaders(headersNotion);
         if(headers != null && !headers.isEmpty()) {
             try{
                 NotionDatabaseDTO notionDatabase = notionClient.getNotionDatabase(urlBaseNotion.getValue(), headers);
                 return notionDatabase;
             }catch (HttpStatusCodeException e){
                 System.out.println(e.getMessage());
             }
         }
         return null;
    }

    public List<NotionItemDTO> notionItemsCompleted(NotionDatabaseDTO notionDatabase){
        List<NotionItemDTO> completedItems = notionDatabase.getItems().parallelStream().filter(i ->
                i.getProperties().getFeito().getCheckbox().equals(true)).collect(Collectors.toList());
        return completedItems;
    }
    public Map<String, Integer> calculateProductivity(List<NotionItemDTO> notionItemsCompleted, List<NotionItemDTO> notionItems) {
        Map<String, Integer> points = new HashMap<>();
        var priorityItemsCompleted = extractPriorityItems(notionItemsCompleted);
        var priorityTotalItems = extractPriorityItems(notionItems);
        var pointsTotalItems = extractPointsItems(priorityTotalItems);
        var pointsCompletedItems = extractPointsItems(priorityItemsCompleted);
        points.put("Total Items", pointsTotalItems);
        points.put("Completed Items", pointsCompletedItems);
        return points;
    }
    public Integer extractPointsItems(List<Integer> priorityItems){
        return  (priorityItems.get(0) * NotionItemPriorityEnum.URGENTE.getWeightEnum())
                + (priorityItems.get(1) * NotionItemPriorityEnum.IMPORTANTE.getWeightEnum())
                + (priorityItems.get(2) * NotionItemPriorityEnum.SEM_PRESSA.getWeightEnum());
    }

    public List<Integer> extractPriorityItems(List<NotionItemDTO> notionItemsCompleted){
        Integer qtdUrgentItems = 0;
        Integer qtdImportantItems = 0;
        Integer qtdUnhurriedItems = 0;
        for (NotionItemDTO item: notionItemsCompleted) {
            String priority = item.getProperties().getPrioridade().getSelect().getName();
            if(priority == null ||priority.isEmpty()) System.out.printf("The item {}, dont priority", item.getId());
            else {
                if (priority.toUpperCase().equals(NotionItemPriorityEnum.URGENTE.toString())) qtdUrgentItems += 1;
                if (priority.toUpperCase().equals(NotionItemPriorityEnum.IMPORTANTE.toString())) qtdImportantItems += 1;
                if (priority.toUpperCase().equals(NotionItemPriorityEnum.SEM_PRESSA.toString())) qtdUnhurriedItems += 1;
            }
        }
        List<Integer> qtdPriorityItems = Arrays.asList(qtdUrgentItems,qtdImportantItems,qtdUnhurriedItems);
        return qtdPriorityItems;
    }

    public Parameter findBaseUrlNotion(){
        return parameterService.findParameterByDescription(ParameterDescriptionEnum.URL_BASE_NOTION.toString());
    }
    public Parameter findHeaderNotion(){
        return parameterService.findParameterByDescription(ParameterDescriptionEnum.HEADERS_NOTION.toString());
    }

}
