package com.ms.productivity.services.impl;

import com.ms.productivity.dtos.ResponseHttpUtilsDTO;
import com.ms.productivity.models.notion.NotionDatabaseIntegration;
import com.ms.productivity.models.notion.NotionIntegration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UtilsServiceImpl {

    public static Map<String, String> buildParametersNotionDatabaseClient(NotionDatabaseIntegration notionDatabaseIntegration,
                                                                   NotionIntegration notionIntegration){
        Map<String, String> notionParametersMap = new HashMap<>();
        notionParametersMap.put("code-database", notionDatabaseIntegration.getIdNotionDatabase());
        notionParametersMap.put("name-database",notionDatabaseIntegration.getName());
        notionParametersMap.put("token", notionIntegration.getNotionParametersIntegration().getToken());
        notionParametersMap.put("notion-version", notionIntegration.getNotionParametersIntegration().getNotionVersion());
        notionParametersMap.put("uri", notionIntegration.getUri().toString());
        return notionParametersMap;
    }

    public static ResponseHttpUtilsDTO successProductivityResponseDTO(){
        var productivityResponse = new ResponseHttpUtilsDTO();
        productivityResponse.setCode(200);
        productivityResponse.setHttpStatus(HttpStatus.OK);
        return productivityResponse;
    }

    public static ResponseHttpUtilsDTO errorProductivityResponseDTO(){
        var productivityResponse = new ResponseHttpUtilsDTO();
        productivityResponse.setCode(404);
        productivityResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        return productivityResponse;
    }

}
