package com.ms.productivity.dtos.integrations;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.net.URI;

@Data
@Builder
public class IntegrationDTO implements Serializable {

    private String name;
    private AuthorizationIntegrationDTO authorizationIntegrationDTO;
    private URI uri;

}
