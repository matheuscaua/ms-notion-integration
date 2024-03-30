package com.ms.notion.dtos.productivity;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ProductivityDTO implements Serializable {

    private LocalDateTime initDate;
    private LocalDateTime endDate;

}
