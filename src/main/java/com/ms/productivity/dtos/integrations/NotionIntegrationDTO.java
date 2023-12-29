package com.ms.productivity.dtos.integrations;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.ms.productivity.models.notion.NotionParametersIntegration;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

@Data
@Builder
public class NotionIntegrationDTO implements Serializable {

    private String name;
    private String uri;

    @JsonProperty("parameters")
    private NotionParametersIntegrationDTO notionParametersIntegration;
}
