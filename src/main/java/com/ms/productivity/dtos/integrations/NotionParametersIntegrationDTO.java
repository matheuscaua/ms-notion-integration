package com.ms.productivity.dtos.integrations;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ms.productivity.enums.NotionHeadersEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotionParametersIntegrationDTO implements Serializable {

    @NotBlank(message = "Invalid Token")
    private String token;

    @NotBlank(message = "Invalid Notion Version")
    @JsonProperty("notion-version")
    private String notionVersion;

}
