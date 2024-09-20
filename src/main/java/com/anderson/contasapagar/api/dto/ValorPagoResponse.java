package com.anderson.contasapagar.api.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record ValorPagoResponse(LocalDate dataInicial, LocalDate dataFinal, BigDecimal valorPago) {
}
