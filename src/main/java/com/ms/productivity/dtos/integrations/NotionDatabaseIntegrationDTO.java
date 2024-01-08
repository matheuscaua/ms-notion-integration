package com.ms.productivity.dtos.integrations;

import com.ms.productivity.enums.NotionHeadersEnum;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
@Builder
public class NotionDatabaseIntegrationDTO implements Serializable {

    private String name;

    private String idNotionDatabase;

}
