package com.ms.productivity.services.impl;

import com.ms.productivity.dtos.ResponseHttpUtilsDTO;
import com.ms.productivity.dtos.integrations.NotionIntegrationDTO;
import com.ms.productivity.models.notion.NotionIntegration;
import com.ms.productivity.models.notion.NotionParametersIntegration;
import com.ms.productivity.repositories.NotionIntegrationRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class NotionIntegrationServiceImpl {

    private final NotionIntegrationRepository repository;

    private static String message;

    public ResponseHttpUtilsDTO<NotionIntegrationDTO> createNotionIntegrationModel(NotionIntegrationDTO notionIntegrationDTO) throws URISyntaxException {
        if(notionIntegrationIsValid(notionIntegrationDTO)){
            var integration = createNotionIntegration(notionIntegrationDTO);
            repository.save(integration);
            return createResponse(notionIntegrationDTO, message, HttpStatus.OK);
        }
        return createResponse(notionIntegrationDTO, message, HttpStatus.BAD_REQUEST);
    }
    private NotionIntegration createNotionIntegration(NotionIntegrationDTO notionIntegrationDTO) throws URISyntaxException {
        var notionIntegrationModel = new NotionIntegration();
        notionIntegrationModel.setName(notionIntegrationDTO.getName());
        notionIntegrationModel.setUri(new URI(notionIntegrationDTO.getUri()));
        var notionParametersIntegration = createNotionParametersIntegration(notionIntegrationDTO);
        notionIntegrationModel.setNotionParametersIntegration(notionParametersIntegration);
        notionIntegrationModel.setLastUpdate(LocalDateTime.now());
        notionIntegrationModel.setSaveDate(LocalDateTime.now());

        return notionIntegrationModel;
    }

    private NotionParametersIntegration createNotionParametersIntegration(NotionIntegrationDTO notionIntegrationDTO){
        var notionParametersIntegration = new NotionParametersIntegration();
        var notionParametersIntegrationDTO = notionIntegrationDTO.getNotionParametersIntegration();
        notionParametersIntegration.setNotionVersion(notionParametersIntegrationDTO.getNotionVersion());
        notionParametersIntegration.setToken(notionParametersIntegrationDTO.getToken());
        return notionParametersIntegration;
    }
    private boolean notionIntegrationIsValid(NotionIntegrationDTO notionIntegrationDTO){
        boolean isValid = true;
        var notionIntegration = repository.findNotionIntegrationByName(notionIntegrationDTO.getName());

        if(!uriNotionIntegrationIsValid(notionIntegrationDTO) ||
                !nameNotionIntegrationIsValid(notionIntegrationDTO)) isValid = false;

        if(notionIntegration.isPresent()) {
            message = "ERROR - Integration exists!";
            isValid = false;
        }
        return isValid;
    }

    private boolean uriNotionIntegrationIsValid(NotionIntegrationDTO notionIntegrationDTO){
        var isValid = notionIntegrationDTO.getUri().startsWith("http") ||
                notionIntegrationDTO.getUri().startsWith("https");
        if(!isValid){
            message = "ERROR - Invalid URI";
        }
        return isValid;
    }

    private boolean nameNotionIntegrationIsValid(NotionIntegrationDTO notionIntegrationDTO){
        var isValid = notionIntegrationDTO.getName().matches("^[a-zA-ZÀ-ÖØ-öø-ÿ]+$");
        if (!isValid){
            message = "ERROR - Invalid Name";
        }
        return isValid;
    }

    private ResponseHttpUtilsDTO<NotionIntegrationDTO> createResponse(NotionIntegrationDTO notionIntegrationDTO,
                                                                     String message,
                                                                     HttpStatus httpStatus){
        var response = new ResponseHttpUtilsDTO<NotionIntegrationDTO>();
        response.setHttpStatus(httpStatus);
        response.setCode(httpStatus.value());
        response.setMessage(message);
        response.setData(notionIntegrationDTO);
        response.setDateTime(LocalDateTime.now());
        return response;
    }

}
