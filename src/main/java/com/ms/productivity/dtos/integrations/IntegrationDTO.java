package com.ms.productivity.dtos.integrations;


import lombok.Data;

import java.io.Serializable;
import java.net.URI;

@Data
public class IntegrationDTO implements Serializable {

    private String name;
    private URI uri;

}
