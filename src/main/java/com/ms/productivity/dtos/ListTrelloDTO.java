package com.ms.productivity.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class ListTrelloDTO {
    private String id;
    private String name;
    private Boolean closed;
    private String idBoard;
    private Integer pos;
    private Boolean subscribed;
    private String softLimit;
    private String status;
}
