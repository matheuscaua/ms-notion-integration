package com.ms.productivity.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class NotionPropertiesItemDTO {

    @JsonProperty("Feito")
    private FeitoDTO feito;
    @JsonProperty("Name")
    private NameDTO name;

    @Data
    public static class FeitoDTO{
        private String id;
        private String type;
        private Boolean checkbox;
    }


    @Data
    public static class NameDTO{
        private String id;
        private String type;
        @JsonProperty("title")
        private List<TitleDTO> title;
    }

    @Data
    public static class TitleDTO{
        @JsonProperty("type")
        private String type;
        @JsonProperty("text")
        private TextDTO text;
    }

    @Data
    public static class TextDTO{
        @JsonProperty("content")
        private String content;
        @JsonProperty("link")
        private String link;
    }
}
