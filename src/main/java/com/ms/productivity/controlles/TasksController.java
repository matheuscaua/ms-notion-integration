package com.ms.productivity.controlles;

import com.ms.productivity.clients.NotionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class TasksController {

    @Autowired
    private NotionClient trelloClient;

    @GetMapping("/")
    public ResponseEntity<?> getNotionDatabase(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(trelloClient.getNotionDatabase());
    }
}
