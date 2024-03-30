package com.ms.notion.utils;

import com.ms.notion.dtos.ResponseHttpUtilsDTO;
import com.ms.notion.models.notion.NotionDatabaseIntegration;
import com.ms.notion.models.notion.NotionIntegration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public static ResponseHttpUtilsDTO<Object> createResponse(Object object,
                                                         String message,
                                                         HttpStatus httpStatus){
        var response = new ResponseHttpUtilsDTO<>();
        response.setHttpStatus(httpStatus);
        response.setCode(httpStatus.value());
        response.setDateTime(LocalDateTime.now());
        response.setMessage(message);
        response.setData(object);
        return response;
    }

}
