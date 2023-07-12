package com.ms.productivity.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardTrelloDTO {

    private String id;
    private String checkItens;
    private String checkItemsChecked;
    @JsonProperty("checkItemsState")
    private CheckItemsStateDTO checkItensState;

}
