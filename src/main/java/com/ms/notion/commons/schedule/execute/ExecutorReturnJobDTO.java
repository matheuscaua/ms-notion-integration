package com.ms.notion.commons.schedule.execute;

import com.ms.notion.enums.StatusJobEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ExecutorReturnJobDTO implements Serializable {

    @Getter private LocalDateTime initDate;
    @Getter private LocalDateTime endDate;
    @Getter @Setter
    private String message;
    @Getter private StatusJobEnum statusJobEnum;
    @Getter @Setter private Serializable response;

    public void finsihed(StatusJobEnum statusJobEnum){
        this.statusJobEnum = statusJobEnum;
        this.endDate = LocalDateTime.now();
    }


}
