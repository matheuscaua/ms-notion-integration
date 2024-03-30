package com.ms.notion.dtos.notion;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class NotionDatabaseDTO {
    private String object;
    private List<PageDTO> results;
}

@Data
class PageDTO {
    private String object;
    private String id;
    @JsonProperty("created_time")
    private String createdTime;
    @JsonProperty("last_edited_time")
    private String lastEditedTime;
    @JsonProperty("created_by")
    private UserDTO createdBy;
    @JsonProperty("last_edited_by")
    private UserDTO lastEditedBy;
    private Object cover;
    private IconDTO icon;
    private ParentDTO parent;
    private boolean archived;
    private PropertiesDTO properties;
    private String url;
    @JsonProperty("public_url")
    private String publicUrl;
}

@Data
class UserDTO {
    private String object;
    private String id;
}

@Data
class IconDTO {
    private String type;
    private ExternalDTO external;
}

@Data
class ExternalDTO {
    private String url;
}

@Data
class ParentDTO {
    private String type;
    @JsonProperty("database_id")
    private String databaseId;
}

@Data
class PropertiesDTO {
    @JsonProperty("Cursos")
    private RelationDTO cursos;
    @JsonProperty("Tema/Assunto")
    private MultiSelectDTO temaAssunto;
    @JsonProperty("Feito")
    private CheckboxDTO feito;
    @JsonProperty("Arquivo")
    private FilesDTO arquivo;
    @JsonProperty("Prioridade")
    private MultiSelectDTO prioridade;
    @JsonProperty("Files & media")
    private FilesDTO filesMedia;
    @JsonProperty("Data")
    private DateDTO data;
    @JsonProperty("Descrição")
    private RichTextDTO descricao;
    @JsonProperty("Nome")
    private TitleDTO nome;
}

@Data
class RelationDTO {
    private String id;
    private String type;
    private List<Object> relation;
    @JsonProperty("has_more")
    private boolean hasMore;
}

@Data
class MultiSelectDTO {
    private String id;
    private String type;
    private List<Object> multiSelect;
}

@Data
class CheckboxDTO {
    private String id;
    private String type;
    private boolean checkbox;
}

@Data
class FilesDTO {
    private String id;
    private String type;
    private List<Object> files;
}

@Data
class DateDTO {
    private String id;
    private String type;
    private DateObjectDTO date;
}

@Data
class DateObjectDTO {
    private String start;
    private String end;
    @JsonProperty("time_zone")
    private Object timeZone;
}

@Data
class RichTextDTO {
    private String id;
    private String type;
    private List<TextObjectDTO> richText;
}

@Data
class TextObjectDTO {
    private String type;
    private TextContentDTO text;
    private TextAnnotationsDTO annotations;
    @JsonProperty("plain_text")
    private String plainText;
    private Object href;
}

@Data
class TextContentDTO {
    private String content;
    private Object link;
}

@Data
class TextAnnotationsDTO {
    private boolean bold;
    private boolean italic;
    private boolean strikethrough;
    private boolean underline;
    private boolean code;
    private String color;
}

@Data
class TitleDTO {
    private String id;
    private String type;
    private List<TextObjectDTO> title;
}
