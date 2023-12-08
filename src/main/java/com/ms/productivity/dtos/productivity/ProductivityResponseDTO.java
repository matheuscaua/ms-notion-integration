package com.ms.productivity.dtos.productivity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Data
@Getter
@Setter
public class ProductivityResponseDTO {

    private HttpStatus httpStatus;
    private int code;

}
