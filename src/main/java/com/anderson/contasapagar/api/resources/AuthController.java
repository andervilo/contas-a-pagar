package com.anderson.contasapagar.api.resources;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.anderson.contasapagar.api.dto.AuthLogin;

@RequestMapping("/auth/token")
@RestController
@Tag(name = "Autenticação", description = "API de Autnticação de Usuários")
public class AuthController {

    @Value("${config.keycloak.loginurl}")
    private String keycloakLoginUrl;

    @PostMapping
    @Operation(summary = "Obter token de autenticação")
    public ResponseEntity<String> token(@Valid @RequestBody AuthLogin user) {

        HttpHeaders headers = new HttpHeaders();
        RestTemplate rt = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", user.clientId());
        formData.add("username", user.username());
        formData.add("password", user.password());
        formData.add("grant_type", user.grantType());

        HttpEntity<MultiValueMap<String, String>> entity
         = new HttpEntity<MultiValueMap<String,String>>(formData, headers);
        
        var result = rt.postForEntity(keycloakLoginUrl, entity, String.class);
    
        return result;
    }
    
}
