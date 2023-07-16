package com.ms.productivity.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class NotionItemDTO {
    private String id;

    @JsonProperty("properties")
    private NotionPropertiesItemDTO properties;
}
