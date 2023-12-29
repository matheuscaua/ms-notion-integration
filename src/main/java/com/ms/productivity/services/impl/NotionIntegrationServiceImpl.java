package com.ms.productivity.services.impl;

import com.ms.productivity.dtos.ResponseHttpUtilsDTO;
import com.ms.productivity.dtos.integrations.NotionIntegrationDTO;
import com.ms.productivity.models.notion.NotionIntegration;
import com.ms.productivity.repositories.NotionIntegrationRepository;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.function.Predicate;

@Service
@AllArgsConstructor
public class NotionIntegrationServiceImpl {

    private final NotionIntegrationRepository repository;

    public ResponseHttpUtilsDTO createNotionIntegrationModel(NotionIntegrationDTO notionIntegrationDTO) throws URISyntaxException {
        var notionIntegrationModel = new NotionIntegration();

        Predicate<NotionIntegrationDTO> isValid = integration -> !integration.getName().isEmpty() ||
                integration.getNotionParametersIntegration().getToken().isEmpty();

        if(isValid.test(notionIntegrationDTO)) {
            BeanUtils.copyProperties(notionIntegrationDTO, notionIntegrationModel);

            notionIntegrationModel.setUri(new URI(notionIntegrationDTO.getUri()));
            notionIntegrationModel.setSaveDate(LocalDateTime.now());
            notionIntegrationModel.setLastUpdate(LocalDateTime.now());

            repository.save(notionIntegrationModel);

            return responseSuccess(notionIntegrationDTO);

        }else return responseError(notionIntegrationDTO);
    }
    private ResponseHttpUtilsDTO responseError(NotionIntegrationDTO notionIntegrationDTO){
        var response = new ResponseHttpUtilsDTO();
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        response.setCode(HttpStatus.NOT_FOUND.value());
        response.setData(notionIntegrationDTO);
        return response;
    }
    private ResponseHttpUtilsDTO responseSuccess(NotionIntegrationDTO notionIntegrationDTO){
        var response = new ResponseHttpUtilsDTO();
        response.setHttpStatus(HttpStatus.CREATED);
        response.setCode(HttpStatus.CREATED.value());
        response.setData(notionIntegrationDTO);
        return response;
    }
}
