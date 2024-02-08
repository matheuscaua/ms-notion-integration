package com.ms.productivity.services.impl;

import com.ms.productivity.dtos.ResponseHttpUtilsDTO;
import com.ms.productivity.dtos.integrations.NotionDatabaseIntegrationDTO;
import com.ms.productivity.dtos.integrations.NotionIntegrationDTO;
import com.ms.productivity.models.notion.NotionDatabaseIntegration;
import com.ms.productivity.models.notion.NotionIntegration;
import com.ms.productivity.repositories.NotionDatabaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotionDatabaseServiceImpl {

    private final NotionDatabaseRepository repository;
    private final NotionIntegrationServiceImpl notionIntegrationService;

    public ResponseHttpUtilsDTO<NotionDatabaseIntegrationDTO> createNotionDatabaseIntegration(NotionDatabaseIntegrationDTO notionDatabaseIntegrationDTO){
        var notionDatabaseIntegration = buildNotionDatabaseIntegration(notionDatabaseIntegrationDTO);
        repository.save(notionDatabaseIntegration);
        return createResponse(notionDatabaseIntegrationDTO, "Created with success! ", HttpStatus.CREATED);
    }

    private NotionDatabaseIntegration buildNotionDatabaseIntegration(NotionDatabaseIntegrationDTO notionDatabaseIntegrationDTO){
        var notionDatabaseIntegration = new NotionDatabaseIntegration();
        var notionIntegration = notionIntegrationService.findNotionIntegrationById(notionDatabaseIntegrationDTO.getNotionIntegrationId());
        if(notionIntegration.isPresent()) {
            notionDatabaseIntegration.setNotionIntegration(notionIntegration.get());
            notionDatabaseIntegration.setName(notionDatabaseIntegrationDTO.getName());
            notionDatabaseIntegration.setIdNotionDatabase(notionDatabaseIntegrationDTO.getIdNotionDatabase());
            return notionDatabaseIntegration;
        }
        return null;
    }
    private ResponseHttpUtilsDTO<NotionDatabaseIntegrationDTO> createResponse(NotionDatabaseIntegrationDTO notionDatabaseIntegrationDTO,
                                                                      String message,
                                                                      HttpStatus httpStatus){
        var response = new ResponseHttpUtilsDTO<NotionDatabaseIntegrationDTO>();
        response.setHttpStatus(httpStatus);
        response.setCode(httpStatus.value());
        response.setMessage(message);
        response.setData(notionDatabaseIntegrationDTO);
        response.setDateTime(LocalDateTime.now());
        return response;
    }
}
