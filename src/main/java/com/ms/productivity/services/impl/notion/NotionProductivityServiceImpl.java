package com.ms.productivity.services.impl.notion;

import com.ms.productivity.clients.NotionClient;
import com.ms.productivity.dtos.notion.NotionDatabaseDTO;
import com.ms.productivity.dtos.notion.NotionItemDTO;
import com.ms.productivity.enums.NotionItemPriorityEnum;
import com.ms.productivity.enums.ParameterDescriptionEnum;
import com.ms.productivity.models.Parameter;
import com.ms.productivity.models.productivity.NotionDatabaseProductivity;
import com.ms.productivity.repositories.ProductivityRepository;
import com.ms.productivity.services.ParameterService;
import com.ms.productivity.services.impl.KafkaTemplateService;
import com.ms.productivity.services.impl.UtilsServiceImpl;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class NotionProductivityServiceImpl {

    private final ProductivityRepository repository;

    private final NotionClient notionClient;

    private final NotionIntegrationServiceImpl notionIntegrationService;

    private final ParameterService parameterService;

    private final NotionDatabaseIntegrationServiceImpl notionDatabaseIntegrationService;

    private final KafkaTemplateService kafkaTemplateService;

    public void calculate(){
        var notionDatabaseIntegrationList = notionDatabaseIntegrationService.findAllNotionDatabaseIntegration();
        notionDatabaseIntegrationList.forEach(notionDatabaseIntegration -> {
           var notionIntegration = notionIntegrationService.findNotionIntegrationById(
                   notionDatabaseIntegration.getNotionIntegration().getId());
           if(notionIntegration.isPresent()) {
                var parametersNotionDatabase = UtilsServiceImpl.buildParametersNotionDatabaseClient(
                        notionDatabaseIntegration, notionIntegration.get());
                NotionDatabaseDTO notionDatabase = notionClient.getNotionDatabaseWithQuery(
                        parametersNotionDatabase.get("uri"), parametersNotionDatabase);
                NotionDatabaseProductivity productivity = new NotionDatabaseProductivity();
                List<NotionItemDTO> allItems = notionDatabase.getItems();
                List<NotionItemDTO> completedItems = notionItemsCompleted(notionDatabase);
                Map<String, Integer> valueProductivity = calculateProductivity(completedItems, allItems);

                productivity = createProductivityModel(productivity,
                        valueProductivity, completedItems.size(), allItems.size());
                save(productivity);
                // kafkaTemplateService
           }
        });
    }

    public NotionDatabaseProductivity createProductivityModel(NotionDatabaseProductivity productivity,
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
                i.getProperties()
                        .getFeito()
                        .getCheckbox()
                        .equals(true))
                .collect(Collectors.toList());
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
    public Parameter findBaseUrlNotion(){
        return parameterService.findParameterByDescription(ParameterDescriptionEnum.URL_BASE_NOTION.toString());
    }
    public Parameter findHeaderNotion(){
        return parameterService.findParameterByDescription(ParameterDescriptionEnum.HEADERS_NOTION.toString());
    }
    public void save(NotionDatabaseProductivity productivity){
        repository.save(productivity);
    }
}
