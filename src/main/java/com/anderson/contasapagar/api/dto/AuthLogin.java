package com.anderson.contasapagar.api.dto;

import com.anderson.contasapagar.api.validators.ValidAuthLogin;

@ValidAuthLogin
public record AuthLogin(
        String password,
        String clientId,
        String grantType,
        String username) {}
