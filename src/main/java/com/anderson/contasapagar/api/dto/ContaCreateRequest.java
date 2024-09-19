package com.anderson.contasapagar.api.dto;

import com.anderson.contasapagar.domain.models.types.SituacaoPagamentoType;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record ContaCreateRequest(
        LocalDate dataVencimento,
        BigDecimal valor,
        String descricao
) {
}
