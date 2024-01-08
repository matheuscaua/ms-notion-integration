package com.ms.productivity.controllers.notion.integrations;

import com.ms.productivity.dtos.ResponseHttpUtilsDTO;
import com.ms.productivity.dtos.integrations.NotionIntegrationDTO;
import com.ms.productivity.services.impl.NotionIntegrationServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/integration")
@AllArgsConstructor
public class NotionIntegrationController {

    private final NotionIntegrationServiceImpl notionIntegrationService;

    @PostMapping
    public ResponseEntity<ResponseHttpUtilsDTO> saveIntegration(@RequestBody NotionIntegrationDTO integrationDTO)
            throws URISyntaxException {
        var response = notionIntegrationService.createNotionIntegrationModel(integrationDTO);
        return ResponseEntity.status(response.getCode()).body(response);
    }

}
