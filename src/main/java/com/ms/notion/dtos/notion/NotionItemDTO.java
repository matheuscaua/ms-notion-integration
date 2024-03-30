package com.ms.notion.dtos.notion;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NotionItemDTO {
    private String id;

    @JsonProperty("properties")
    private NotionPropertiesItemDTO properties;
}
