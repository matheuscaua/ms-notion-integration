package com.ms.productivity.controlles;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@AllArgsConstructor
@Component
public class NotionDatabaseAgendador {
    private static final String ALL_DAY_12_HOURS_PM = "0 0 12 * * ?";

    private final NotionDatabaseJobController notionDatabaseJobController;
    @Scheduled(cron = ALL_DAY_12_HOURS_PM)
    public void execute(){
        notionDatabaseJobController.execute();
    }

}
