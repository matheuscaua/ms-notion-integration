package com.ms.productivity.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum NotionHeadersEnum {

    AUTHORIZATION("Authorization"),
    NOTION_VERSION("Notion-Version");

    final String description;

}
