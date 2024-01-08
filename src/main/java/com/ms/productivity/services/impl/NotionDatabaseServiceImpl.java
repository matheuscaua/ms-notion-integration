package com.ms.productivity.services.impl;

import com.ms.productivity.dtos.integrations.NotionDatabaseIntegrationDTO;
import com.ms.productivity.repositories.NotionDatabaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotionDatabaseServiceImpl {

    private final NotionDatabaseRepository repository;

}
