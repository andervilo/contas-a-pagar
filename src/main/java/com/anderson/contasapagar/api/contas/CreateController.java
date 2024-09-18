package com.anderson.contasapagar.api.contas;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/contas")
public class CreateController {
    
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("buscando Contas a Pagar!");
    }
}
