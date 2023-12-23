package com.ms.productivity.controllers.integrations;

import com.ms.productivity.dtos.integrations.NotionIntegrationDTO;
import com.ms.productivity.services.impl.NotionIntegrationServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/integration")
@AllArgsConstructor
public class NotionIntegrationController {

    private final NotionIntegrationServiceImpl notionIntegrationService;

    @PostMapping
    public ResponseEntity saveIntegration(@RequestBody NotionIntegrationDTO integrationDTO){

        notionIntegrationService.save(integrationDTO);

        return new ResponseEntity(HttpStatus.OK);
    }
}
