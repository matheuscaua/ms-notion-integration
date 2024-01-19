package com.ms.productivity.controllers.notion.productivity;

import com.ms.productivity.dtos.ResponseHttpUtilsDTO;
import com.ms.productivity.services.impl.NotionProductivityServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/calculate")
    public ResponseEntity<ResponseHttpUtilsDTO> calculateProductivity(){
        var response = productivityService.calculate();
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }


}
