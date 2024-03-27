package com.ms.productivity.clients;

import com.ms.productivity.dtos.notion.NotionDatabaseDTO;
import com.ms.productivity.services.impl.UtilsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Component
public class NotionClient {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UtilsServiceImpl utilsService;

    public NotionDatabaseDTO getNotionDatabaseWithQuery(String baseUrl, Map<String, String> headers){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBearerAuth(headers.get("token"));
        httpHeaders.set("Notion-Version", headers.get("notion-version"));
        HttpEntity<NotionDatabaseDTO> httpEntity = new HttpEntity<>(httpHeaders);
        ParameterizedTypeReference<NotionDatabaseDTO> responseType = new ParameterizedTypeReference<>() {};
        ResponseEntity<NotionDatabaseDTO> response = restTemplate.exchange(baseUrl, HttpMethod.POST, httpEntity, responseType);

        return response.getBody();
    }

}
