package com.anderson.contasapagar.api.dto;

public record AuthLogin(String password, String clientId, String grantType, String username) {}
