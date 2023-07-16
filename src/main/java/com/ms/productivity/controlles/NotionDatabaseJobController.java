package com.ms.productivity.controlles;

import com.ms.productivity.clients.NotionClient;
import com.ms.productivity.dtos.NotionDatabaseDTO;
import com.ms.productivity.dtos.NotionItemDTO;
import com.ms.productivity.enums.ParameterDescriptionEnum;
import com.ms.productivity.models.Parameter;
import com.ms.productivity.services.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class NotionDatabaseJobController {

    @Autowired
    private NotionClient notionClient;

    @Autowired
    private ParameterService parameterService;

    public void execute(){
        NotionDatabaseDTO notionDatabase = findNotionDatabase();
        notionItemsCompleted(notionDatabase).forEach(i -> System.out.println(i.toString()));
    }

    public Parameter findBaseUrlNotion(){
        return parameterService.findParameterByDescription(ParameterDescriptionEnum.URL_BASE_NOTION.toString());
    }
    public Parameter findHeaderNotion(){
        return parameterService.findParameterByDescription(ParameterDescriptionEnum.HEADERS_NOTION.toString());
    }
    public NotionDatabaseDTO findNotionDatabase (){
        Parameter urlBaseNotion = findBaseUrlNotion();
        Parameter headersNotion = findHeaderNotion();
        Map<String, String> headers = parameterService.extractNotionHeaders(headersNotion);
        return notionClient.getNotionDatabase(urlBaseNotion.getValue(), headers);
    }

    public List<NotionItemDTO> notionItemsCompleted(NotionDatabaseDTO notionDatabase){
        List<NotionItemDTO> completedItems = notionDatabase.getItems().stream().filter(i ->
                i.getProperties().getFeito().getCheckbox().equals(true)).collect(Collectors.toList());
        return completedItems;
    }

    public Map<Long, Long> calculateProductivity(List<NotionItemDTO> notionItemsCompleted){
        var qtdItemsCompleted = notionItemsCompleted.size();
        notionItemsCompleted.stream().forEach(i -> {
            String priority = i.getProperties().getPrioridade().getSelect().getName();
            if (priority.equals())
        });


    }



}
