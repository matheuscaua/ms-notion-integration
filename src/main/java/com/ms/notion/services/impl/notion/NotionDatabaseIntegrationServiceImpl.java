package com.ms.notion.services.impl.notion;

import com.ms.notion.dtos.ResponseHttpUtilsDTO;
import com.ms.notion.dtos.integrations.NotionDatabaseIntegrationDTO;
import com.ms.notion.models.notion.NotionDatabaseIntegration;
import com.ms.notion.repositories.NotionDatabaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NotionDatabaseIntegrationServiceImpl {

    private final NotionDatabaseRepository repository;

    private final NotionIntegrationServiceImpl notionIntegrationService;

    public ResponseHttpUtilsDTO<NotionDatabaseIntegrationDTO> createNotionDatabaseIntegration(NotionDatabaseIntegrationDTO notionDatabaseIntegrationDTO){
        var notionDatabaseIntegration = buildNotionDatabaseIntegration(notionDatabaseIntegrationDTO);
        if (notionDatabaseIntegration.isPresent()) {
            repository.save(notionDatabaseIntegration.get());
            return createResponse(notionDatabaseIntegrationDTO, "Created with success! ", HttpStatus.CREATED);
        }else return createResponse(notionDatabaseIntegrationDTO, "Error - Incorrect Payload! ", HttpStatus.BAD_REQUEST);
    }

    private Optional<NotionDatabaseIntegration> buildNotionDatabaseIntegration(NotionDatabaseIntegrationDTO notionDatabaseIntegrationDTO){
        var notionDatabaseIntegration = new NotionDatabaseIntegration();
        var notionIntegration = notionIntegrationService.findNotionIntegrationById(notionDatabaseIntegrationDTO.getNotionIntegrationId());
        if(notionIntegration.isPresent()) {
            notionDatabaseIntegration.setNotionIntegration(notionIntegration.get());
            notionDatabaseIntegration.setName(notionDatabaseIntegrationDTO.getName());
            notionDatabaseIntegration.setIdNotionDatabase(notionDatabaseIntegrationDTO.getIdNotionDatabase());
            return Optional.of(notionDatabaseIntegration);
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
