package com.ms.productivity.controllers.notion.integrations;

import com.ms.productivity.dtos.ResponseHttpUtilsDTO;
import com.ms.productivity.dtos.integrations.NotionDatabaseIntegrationDTO;
import com.ms.productivity.services.impl.notion.NotionDatabaseIntegrationServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/database")
@AllArgsConstructor
public class NotionDatabaseController {

    private final NotionDatabaseIntegrationServiceImpl notionDatabaseService;

    @PostMapping
    public ResponseEntity<ResponseHttpUtilsDTO<NotionDatabaseIntegrationDTO>> saveIntegration(
            @RequestBody NotionDatabaseIntegrationDTO notionDatabaseIntegrationDTO) {
        var response = notionDatabaseService.createNotionDatabaseIntegration(notionDatabaseIntegrationDTO);
        return ResponseEntity.status(response.getCode()).body(response);
    }

}
