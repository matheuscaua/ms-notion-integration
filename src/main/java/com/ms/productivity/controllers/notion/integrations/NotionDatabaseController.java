package com.ms.productivity.controllers.notion.integrations;

import com.ms.productivity.dtos.ResponseHttpUtilsDTO;
import com.ms.productivity.dtos.integrations.NotionDatabaseIntegrationDTO;
import com.ms.productivity.dtos.integrations.NotionIntegrationDTO;
import com.ms.productivity.services.impl.NotionDatabaseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/database")
@AllArgsConstructor
public class NotionDatabaseController {


    private final NotionDatabaseServiceImpl notionDatabaseService;

    @PostMapping
    public ResponseEntity<ResponseHttpUtilsDTO<NotionDatabaseIntegrationDTO>> saveIntegration(
            @RequestBody NotionDatabaseIntegrationDTO notionDatabaseIntegrationDTO) throws URISyntaxException {
        var response = notionDatabaseService.createNotionDatabaseIntegration(notionDatabaseIntegrationDTO);
        return ResponseEntity.status(response.getCode()).body(response);
    }

}
