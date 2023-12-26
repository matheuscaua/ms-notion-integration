package com.ms.productivity.services.impl;

import com.ms.productivity.dtos.integrations.NotionIntegrationDTO;
import com.ms.productivity.models.notion.NotionIntegration;
import com.ms.productivity.repositories.NotionIntegrationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@AllArgsConstructor
public class NotionIntegrationServiceImpl {

    private final NotionIntegrationRepository repository;

    public void save(NotionIntegrationDTO integrationDTO){
        var notionIntegration = createNotionIntegrarionModel(integrationDTO);
        repository.save(notionIntegration);
    }

    private NotionIntegration createNotionIntegrarionModel(NotionIntegrationDTO notionIntegrationDTO){
        var notionIntegrationModel = new NotionIntegration();
        if (verifyNotionIntegrationDTO(notionIntegrationDTO)){
            throw new RuntimeException();
        }

        save(notionIntegrationDTO);
        return  notionIntegrationModel;
    }

    private boolean verifyNotionIntegrationDTO(NotionIntegrationDTO notionIntegrationDTO){
        return notionIntegrationDTO.getName().isEmpty() || notionIntegrationDTO.getToken().isEmpty() ||
                notionIntegrationDTO.getAuthorizationIntegrationDTO().getHeaders().isEmpty();
    }
}
