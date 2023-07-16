package com.ms.productivity.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NotionPropertiesItemDTO {

    @JsonProperty("Feito")
    private FeitoDTO feito;

    @Data
    public class FeitoDTO{
        private String id;
        private String type;
        private Boolean checkbox;
    }
}
