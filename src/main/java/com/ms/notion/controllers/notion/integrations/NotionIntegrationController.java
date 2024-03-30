package com.ms.notion.controllers.notion.integrations;

import com.ms.notion.dtos.ResponseHttpUtilsDTO;
import com.ms.notion.dtos.integrations.NotionIntegrationDTO;
import com.ms.notion.services.impl.notion.NotionIntegrationServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1/integration")
@AllArgsConstructor
public class NotionIntegrationController {

    private final NotionIntegrationServiceImpl notionIntegrationService;

    @PostMapping
    public ResponseEntity<ResponseHttpUtilsDTO<NotionIntegrationDTO>> saveIntegration(@RequestBody NotionIntegrationDTO integrationDTO)
            throws URISyntaxException {
        var response = notionIntegrationService.createNotionIntegrationModel(integrationDTO);
        return ResponseEntity.status(response.getCode()).body(response);

    }

}
