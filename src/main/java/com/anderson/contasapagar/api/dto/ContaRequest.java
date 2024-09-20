package com.anderson.contasapagar.api.dto;

import com.anderson.contasapagar.domain.models.types.SituacaoPagamentoType;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record ContaRequest(
        Long id,
        LocalDate dataVencimento,
        LocalDateTime dataPagamento,
        BigDecimal valor,
        String descricao,
        SituacaoPagamentoType situacao
) {
}
