package com.ms.productivity.controlles;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NotionDatabaseAgendador {
    private static final String TIME = "*/2 * * * * *";

    private NotionDatabaseJobController notionDatabaseJobController;
    @Scheduled(cron = TIME)
    public void execute(){
        notionDatabaseJobController.execute();
    }

}
