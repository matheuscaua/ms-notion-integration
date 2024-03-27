package com.ms.productivity.dtos.integrations;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.ms.productivity.models.notion.NotionIntegration;
import com.ms.productivity.models.notion.NotionParametersIntegration;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotionIntegrationDTO implements Serializable {

    public interface NotionIntegrationView{
        interface NotionIntegrationSave{}
    }
    
    @NotBlank(groups = NotionIntegrationView.NotionIntegrationSave.class)
    @Size(min = 3, max = 50, groups = NotionIntegrationView.NotionIntegrationSave.class)
    @JsonView(NotionIntegrationView.NotionIntegrationSave.class)
    private String name;

    @NotBlank(groups = NotionIntegrationView.NotionIntegrationSave.class)
    @JsonView(NotionIntegrationView.NotionIntegrationSave.class)
    private String uri;

    @NotBlank(groups = NotionIntegrationView.NotionIntegrationSave.class)
    @JsonView(NotionIntegrationView.NotionIntegrationSave.class)
    @JsonProperty("parameters")
    private NotionParametersIntegrationDTO notionParametersIntegration;
}
