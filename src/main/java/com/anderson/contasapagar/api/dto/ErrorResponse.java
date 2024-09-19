package com.anderson.contasapagar.api.dto;

import lombok.Builder;

@Builder
public record ErrorResponse(
        String timestamp,
        int status,
        String error,
        Object message
) {
}
