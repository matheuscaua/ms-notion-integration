package com.ms.notion.dtos.integrations;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotionDatabaseIntegrationDTO implements Serializable {

    @JsonProperty("name")
    private String name;
    @JsonProperty("database-id")
    private String idNotionDatabase;
    @JsonProperty("integration-id")
    private UUID notionIntegrationId;

}
