package com.ms.productivity.controlles.notion;

import com.ms.productivity.dtos.productivity.ProductivityDTO;
import com.ms.productivity.dtos.productivity.ProductivityResponseDTO;
import com.ms.productivity.services.impl.ProductivityServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* *
 * Essa classe realiza o cálculo para a produtividade
 * */

@RestController
@RequestMapping("/productivity")
@Slf4j
public class NotionDatabaseController {

    @Autowired
    private ProductivityServiceImpl productivityService;

    @PostMapping("/calculate")
    public ResponseEntity<ProductivityResponseDTO> calculateProductivity(ProductivityDTO productivityDTO){
        var response = productivityService.calculate();
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }


}
