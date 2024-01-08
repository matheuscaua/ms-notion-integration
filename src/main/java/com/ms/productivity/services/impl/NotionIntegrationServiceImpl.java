package com.ms.productivity.services.impl;

import com.ms.productivity.dtos.ResponseHttpUtilsDTO;
import com.ms.productivity.dtos.integrations.NotionIntegrationDTO;
import com.ms.productivity.models.notion.NotionIntegration;
import com.ms.productivity.models.notion.NotionParametersIntegration;
import com.ms.productivity.repositories.NotionIntegrationRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class NotionIntegrationServiceImpl {

    private final NotionIntegrationRepository repository;

    public ResponseHttpUtilsDTO<NotionIntegrationDTO> createNotionIntegrationModel(NotionIntegrationDTO notionIntegrationDTO) throws URISyntaxException {
        if(notionIntegrationIsValid(notionIntegrationDTO)){
            repository.save(createNotionIntegration(notionIntegrationDTO));
            return responseSuccess(notionIntegrationDTO);
        }
        return responseError(notionIntegrationDTO);
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
        if(!uriNotionIntegrationIsValid(notionIntegrationDTO) || !nameNotionIntegrationIsValid(notionIntegrationDTO))
            isValid = false;

        return isValid;

    }

    private boolean uriNotionIntegrationIsValid(NotionIntegrationDTO notionIntegrationDTO){
        return notionIntegrationDTO.getUri().startsWith("http") ||
                notionIntegrationDTO.getUri().startsWith("https");
    }

    private boolean nameNotionIntegrationIsValid(NotionIntegrationDTO notionIntegrationDTO){
        return notionIntegrationDTO.getName().matches("^[a-zA-ZÀ-ÖØ-öø-ÿ]+$"); //Verifica se contém apenas letras e caracteres unicode.
    }

    private ResponseHttpUtilsDTO<NotionIntegrationDTO> responseError(NotionIntegrationDTO notionIntegrationDTO){
        var response = new ResponseHttpUtilsDTO<NotionIntegrationDTO>();
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        response.setCode(HttpStatus.NOT_FOUND.value());
        response.setData(notionIntegrationDTO);
        return response;
    }
    private ResponseHttpUtilsDTO<NotionIntegrationDTO> responseSuccess(NotionIntegrationDTO notionIntegrationDTO){
        var response = new ResponseHttpUtilsDTO<NotionIntegrationDTO>();
        response.setHttpStatus(HttpStatus.CREATED);
        response.setCode(HttpStatus.CREATED.value());
        response.setData(notionIntegrationDTO);
        return response;
    }
}
