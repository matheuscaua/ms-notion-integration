package com.ms.notion.dtos.integrations;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotionParametersIntegrationDTO implements Serializable {

    @NotBlank(message = "Invalid Token")
    @JsonProperty("token")
    private String token;

    @NotBlank(message = "Invalid Notion Version")
    @JsonProperty("notion-version")
    private String notionVersion;

}
