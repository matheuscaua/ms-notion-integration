package com.ms.productivity.controllers.integrations;

import com.ms.productivity.dtos.integrations.IntegrationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/integration")
public class NotionIntegrationController {

    @PostMapping
    public ResponseEntity saveIntegration(@RequestBody IntegrationDTO integrationDTO){

        return new ResponseEntity(HttpStatus.OK);
    }
}
