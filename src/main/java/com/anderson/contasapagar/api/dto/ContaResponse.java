package com.anderson.contasapagar.api.dto;

import com.anderson.contasapagar.domain.models.types.SituacaoPagamentoType;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ContaResponse(
        Long id,
        String dataVencimento,
        String dataPagamento,
        BigDecimal valor,
        String descricao,
        SituacaoPagamentoType situacao
) {
}
