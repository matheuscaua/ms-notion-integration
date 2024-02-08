package com.ms.productivity.dtos.integrations;

import com.ms.productivity.enums.NotionHeadersEnum;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
public class NotionDatabaseIntegrationDTO implements Serializable {

    private String name;

    private String idNotionDatabase;

    private UUID notionIntegrationId;

}
