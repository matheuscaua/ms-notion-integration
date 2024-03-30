package com.ms.notion.controllers.notion.productivity;

import com.ms.notion.services.impl.notion.NotionProductivityServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* *
 * Essa classe realiza o cálculo para a produtividade
 * */

@RestController
@RequestMapping("/productivity")
@AllArgsConstructor
@Slf4j
public class NotionProductivityController {

    private final NotionProductivityServiceImpl productivityService;

}
