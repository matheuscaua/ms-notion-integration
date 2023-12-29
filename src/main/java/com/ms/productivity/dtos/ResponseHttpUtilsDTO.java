package com.ms.productivity.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@Getter
@Setter
public class ResponseHttpUtilsDTO implements Serializable {

    private HttpStatus httpStatus;
    private int code;
    private Object data;

}
