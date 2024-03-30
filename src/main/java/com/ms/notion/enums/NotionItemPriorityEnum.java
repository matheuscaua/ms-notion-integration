package com.ms.notion.enums;

public enum NotionItemPriorityEnum {
    URGENTE(3),
    IMPORTANTE(2),
    SEM_PRESSA(1);

    final Integer weight;
    NotionItemPriorityEnum(Integer weight) {
        this.weight = weight;
    }

    public Integer getWeightEnum(){
        return weight;
    }
}
