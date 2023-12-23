package com.ms.productivity.dtos.integrations;

import com.ms.productivity.enums.NotionHeadersEnum;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class NotionParametersDatabaseDTO {

    private Map<NotionHeadersEnum, String> headers;


}
