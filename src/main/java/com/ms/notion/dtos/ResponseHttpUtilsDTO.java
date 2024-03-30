package com.ms.notion.dtos;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class ResponseHttpUtilsDTO<T> implements Serializable {

    private HttpStatus httpStatus;
    private int code;
    private String message;
    private LocalDateTime dateTime;
    private T data;

}
