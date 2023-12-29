package com.ms.productivity.dtos.notion;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotionDatabaseDTO {

    @JsonProperty("object")
    private String object;

    @JsonProperty("results")
    private List<NotionItemDTO> items;

    private
}
