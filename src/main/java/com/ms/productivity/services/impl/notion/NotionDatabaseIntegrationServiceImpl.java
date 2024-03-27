package com.ms.productivity.services.impl.notion;

import com.ms.productivity.dtos.ResponseHttpUtilsDTO;
import com.ms.productivity.dtos.integrations.NotionDatabaseIntegrationDTO;
import com.ms.productivity.models.notion.NotionDatabaseIntegration;
import com.ms.productivity.repositories.NotionDatabaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class NotionDatabaseIntegrationServiceImpl {

    private final NotionDatabaseRepository repository;

    private final NotionIntegrationServiceImpl notionIntegrationService;

    public ResponseHttpUtilsDTO<NotionDatabaseIntegrationDTO> createNotionDatabaseIntegration(NotionDatabaseIntegrationDTO notionDatabaseIntegrationDTO){
        var notionDatabaseIntegration = buildNotionDatabaseIntegration(notionDatabaseIntegrationDTO);
        if (notionDatabaseIntegration != null) {
            repository.save(notionDatabaseIntegration);
            return createResponse(notionDatabaseIntegrationDTO, "Created with success! ", HttpStatus.CREATED);
        }else return createResponse(notionDatabaseIntegrationDTO, "Error - Incorrect Payload! ", HttpStatus.BAD_REQUEST);
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
    public List<NotionDatabaseIntegration> findAllNotionDatabaseIntegration(){
        return repository.findAll();
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
