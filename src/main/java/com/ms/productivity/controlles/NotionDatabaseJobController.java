package com.ms.productivity.controlles;

import com.ms.productivity.clients.NotionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NotionDatabaseJobController {
    @Autowired
    private NotionClient notionClient;


    @Scheduled(cron = "*/2 * * * * *")
    private void execute(){
        System.out.println(notionClient.getNotionDatabase().toString());
    }

}
