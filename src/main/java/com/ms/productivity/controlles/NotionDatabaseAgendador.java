package com.ms.productivity.controlles;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@AllArgsConstructor
@Component
public class NotionDatabaseAgendador {
    private static final String TIME = "*/2 * * * * *";

    private final NotionDatabaseJobController notionDatabaseJobController;
    @Scheduled(cron = TIME)
    public void execute(){
        notionDatabaseJobController.execute();
    }

}
