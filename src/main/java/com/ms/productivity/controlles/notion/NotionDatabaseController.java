package com.ms.productivity.controlles.notion;

import com.ms.productivity.dtos.productivity.ProductivityDTO;
import com.ms.productivity.dtos.productivity.ProductivityResponseDTO;
import com.ms.productivity.services.impl.ProductivityServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* *
 * Essa classe realiza o cálculo para a produtividade
 * */

@RestController
@RequestMapping("/productivity")
@AllArgsConstructor
@Slf4j
public class NotionDatabaseController {

    private final ProductivityServiceImpl productivityService;

    @GetMapping("/calculate")
    public ResponseEntity<ProductivityResponseDTO> calculateProductivity(){
        var response = productivityService.calculate();
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }


}
