package com.ms.productivity.services.impl;

import com.ms.productivity.dtos.integrations.NotionIntegrationDTO;
import com.ms.productivity.models.notion.NotionIntegration;
import com.ms.productivity.repositories.NotionIntegrationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotionIntegrationServiceImpl {

    private final NotionIntegrationRepository repository;

    public void save(NotionIntegrationDTO notionIntegrationDTO) throws URISyntaxException {
        var notionIntegrationModel = createNotionIntegrarionModel(notionIntegrationDTO);
        repository.save(notionIntegrationModel);
    }

    private NotionIntegration createNotionIntegrarionModel(NotionIntegrationDTO notionIntegrationDTO) throws URISyntaxException {
        var notionIntegrationModel = new NotionIntegration();

        if (verifyNotionIntegrationDTO(notionIntegrationDTO)){
            throw new RuntimeException();
        }
        BeanUtils.copyProperties(notionIntegrationDTO, notionIntegrationModel);
        notionIntegrationModel.setUri(new URI(notionIntegrationDTO.getUri()));
        notionIntegrationModel.setSaveDate(LocalDateTime.now());
        notionIntegrationModel.setLastUpdate(LocalDateTime.now());

        return  notionIntegrationModel;
    }

    private boolean verifyNotionIntegrationDTO(NotionIntegrationDTO notionIntegrationDTO){
        return notionIntegrationDTO.getName().isEmpty() || notionIntegrationDTO.getToken().isEmpty();
    }

}
