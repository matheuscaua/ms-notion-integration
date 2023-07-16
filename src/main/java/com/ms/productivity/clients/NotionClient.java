package com.ms.productivity.clients;

import com.ms.productivity.dtos.NotionDatabaseDTO;
import com.ms.productivity.services.UtilsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class NotionClient {

    @Value("${trello.api.base.url}")
    private String BASE_URL;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UtilsServiceImpl utilsService;

    public NotionDatabaseDTO getNotionDatabase(String baseUrl, Map<String, String> headers){
        String url = utilsService.createUrlGetNotionDatabase(baseUrl);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<NotionDatabaseDTO> httpEntity = new HttpEntity<>(httpHeaders);
        ParameterizedTypeReference<NotionDatabaseDTO> responseType = new ParameterizedTypeReference<NotionDatabaseDTO>() {};
        ResponseEntity<NotionDatabaseDTO> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, responseType);

        return response.getBody();
    }

}
