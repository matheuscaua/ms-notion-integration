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

@Controller
public class NotionDatabaseJobController {

    @Autowired
    private NotionClient notionClient;

    @Autowired
    private ParameterService parameterService;

    public void execute(){

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
        return notionClient.getNotionDatabase(headersNotion.getValue(),urlBaseNotion.getValue());
    }




}