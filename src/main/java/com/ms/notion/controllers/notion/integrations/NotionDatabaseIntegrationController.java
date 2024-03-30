package com.ms.notion.controllers.notion.integrations;

import com.ms.notion.dtos.ResponseHttpUtilsDTO;
import com.ms.notion.dtos.integrations.NotionDatabaseIntegrationDTO;
import com.ms.notion.services.impl.notion.NotionDatabaseIntegrationServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/database")
@AllArgsConstructor
public class NotionDatabaseIntegrationController {

    private final NotionDatabaseIntegrationServiceImpl notionDatabaseService;

    @PostMapping("/")
    public ResponseEntity<ResponseHttpUtilsDTO<NotionDatabaseIntegrationDTO>> saveIntegration(
            @RequestBody NotionDatabaseIntegrationDTO notionDatabaseIntegrationDTO) {
        var response = notionDatabaseService.createNotionDatabaseIntegration(notionDatabaseIntegrationDTO);
        return ResponseEntity.status(response.getCode()).body(response);
    }

}
