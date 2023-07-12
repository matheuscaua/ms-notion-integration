package com.ms.productivity.controlles;

import com.ms.productivity.clients.TrelloClient;
import com.ms.productivity.dtos.ListTrelloDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    @Autowired
    private TrelloClient trelloClient;

    @GetMapping("/")
    public ResponseEntity<?> getAllLists(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(trelloClient.getAllListsOnABoard());
    }
}
