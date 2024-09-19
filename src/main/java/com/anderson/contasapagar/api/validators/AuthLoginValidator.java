package com.anderson.contasapagar.api.validators;

import com.anderson.contasapagar.api.dto.AuthLogin;
import com.anderson.contasapagar.domain.exceptions.CustomException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashMap;
import java.util.Map;

public class AuthLoginValidator implements ConstraintValidator<ValidAuthLogin, AuthLogin> {

    @Override
    public boolean isValid(AuthLogin authLogin, ConstraintValidatorContext context) {
        Map<String, String> errors = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        boolean isValid = true;

        if (authLogin.password() == null || authLogin.password().isEmpty()) {
            errors.put("password", "Password não pode ser vazio");
            isValid = false;
        }

        if (authLogin.clientId() == null || authLogin.clientId().isEmpty()) {
            errors.put("clientId", "Client Id não pode ser vazio");
            isValid = false;
        }

        if (authLogin.grantType() == null || authLogin.grantType().isEmpty()) {
            errors.put("grantType", "Grant Type não pode ser vazio");
            isValid = false;
        }

        if (authLogin.username() == null || authLogin.username().isEmpty()) {
            errors.put("username", "Username não pode ser vazio");
            isValid = false;
        }

        if (!isValid) {
            try {
                throw new CustomException(400, mapper.writeValueAsString(errors));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return isValid;
    }
}
