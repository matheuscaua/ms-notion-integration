package com.ms.productivity.dtos;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@Getter
@Setter
public class ResponseHttpUtilsDTO<T> implements Serializable {

    private HttpStatus httpStatus;
    private int code;
    private T data;

}
