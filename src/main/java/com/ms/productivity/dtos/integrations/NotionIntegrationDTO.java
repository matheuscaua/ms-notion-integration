package com.ms.productivity.dtos.integrations;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

@Data
@Builder
public class NotionIntegrationDTO implements Serializable {

    private String name;
    private String token;
    private String uri;
}
