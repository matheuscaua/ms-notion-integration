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
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Controller
public class NotionDatabaseJobController {

    @Autowired
    private NotionClient notionClient;

    @Autowired
    private ParameterService parameterService;

    @Autowired
    private ProductivityServiceImpl productivityService;

    public void execute(){
        NotionDatabaseDTO notionDatabase = findNotionDatabase();
        List<NotionItemDTO> notionItemsCompleted = notionItemsCompleted(notionDatabase);
        Integer valueProductivity = calculateProductivity(notionItemsCompleted);
        Productivity productivity = productivityService.createProductivityModel(valueProductivity);
        productivityService.save(productivity);
    }

    public NotionDatabaseDTO findNotionDatabase(){
        Parameter urlBaseNotion = findBaseUrlNotion();
        Parameter headersNotion = findHeaderNotion();
        Map<String, String> headers = parameterService.extractNotionHeaders(headersNotion);
        return notionClient.getNotionDatabase(urlBaseNotion.getValue(), headers);
    }

    public List<NotionItemDTO> notionItemsCompleted(NotionDatabaseDTO notionDatabase){
        List<NotionItemDTO> completedItems = notionDatabase.getItems().parallelStream().filter(i ->
                i.getProperties().getFeito().getCheckbox().equals(true)).collect(Collectors.toList());
        return completedItems;
    }
    public Integer calculateProductivity(List<NotionItemDTO> notionItemsCompleted) {
        var items = extractPriorityItems(notionItemsCompleted);
        Integer totalPoints = (items.get(1) * NotionItemPriorityEnum.URGENTE.getWeightEnum())
                + (items.get(2) * NotionItemPriorityEnum.IMPORTANTE.getWeightEnum())
                + (items.get(3) * NotionItemPriorityEnum.SEM_PRESSA.getWeightEnum());

        Integer productivity = totalPoints / items.get(0);

        return productivity;
    }

    public List<Integer> extractPriorityItems(List<NotionItemDTO> notionItemsCompleted){
        var qtdCompletedItems = notionItemsCompleted.size();
        Integer qtdUrgentItems = 0;
        Integer qtdImportantItems = 0;
        Integer qtdUnhurriedItems = 0;
        for (NotionItemDTO item: notionItemsCompleted) {
            String priority = item.getProperties().getPrioridade().getSelect().getName();
            if (priority.toUpperCase().equals(NotionItemPriorityEnum.URGENTE.toString())) qtdUrgentItems += 1;
            if (priority.toUpperCase().equals(NotionItemPriorityEnum.IMPORTANTE.toString())) qtdImportantItems += 1;
            if (priority.toUpperCase().equals(NotionItemPriorityEnum.SEM_PRESSA.toString())) qtdUnhurriedItems += 1;
        }
        List<Integer> qtdItems = Arrays.asList(qtdCompletedItems,qtdUrgentItems,qtdImportantItems,qtdUnhurriedItems);
        return qtdItems;
    }

    public Parameter findBaseUrlNotion(){
        return parameterService.findParameterByDescription(ParameterDescriptionEnum.URL_BASE_NOTION.toString());
    }
    public Parameter findHeaderNotion(){
        return parameterService.findParameterByDescription(ParameterDescriptionEnum.HEADERS_NOTION.toString());
    }

}
