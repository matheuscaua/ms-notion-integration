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

    public NotionDatabaseDTO getNotionDatabase(){
        String urlList = utilsService.createUrlGetAllListsOnABoard(BASE_URL);

        Map<String, String> valueHeaders = new HashMap<>();
        valueHeaders.put("Authorization", "Bearer secret_VxyOZPpQQaPAkCuXIoVv2VXNMVMvc6Y5SWy3asFbizb");
        valueHeaders.put("Notion-Version", "2022-06-28");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAll(valueHeaders);
        HttpEntity<NotionDatabaseDTO> httpEntity = new HttpEntity<>(headers);
        ParameterizedTypeReference<NotionDatabaseDTO> responseType = new ParameterizedTypeReference<NotionDatabaseDTO>() {};

        ResponseEntity<NotionDatabaseDTO> response = restTemplate.exchange(urlList, HttpMethod.POST, httpEntity, responseType);

        return response.getBody();
    }

}
