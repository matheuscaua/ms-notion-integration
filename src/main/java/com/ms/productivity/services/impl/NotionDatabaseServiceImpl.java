package com.ms.productivity.services.impl;

import com.ms.productivity.dtos.integrations.NotionDatabaseIntegrationDTO;
import com.ms.productivity.repositories.NotionDatabaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotionDatabaseServiceImpl {

    private final NotionDatabaseRepository repository;

    public void saveNotionDatabase(NotionDatabaseIntegrationDTO notionDatabaseIntegrationDTO){

        repository.save();
    }

    private boolean isValid(NotionDatabaseIntegrationDTO integrationDTO){
        integrationDTO.get
    }

}
