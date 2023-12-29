package com.ms.productivity.controllers.notion.integrations;

import com.ms.productivity.services.impl.NotionDatabaseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/database")
@AllArgsConstructor
public class NotionDatabaseController {


    private final NotionDatabaseServiceImpl notionDatabaseService;



}
