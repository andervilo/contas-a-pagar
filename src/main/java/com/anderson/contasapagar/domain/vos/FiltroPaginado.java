package com.anderson.contasapagar.domain.vos;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record FiltroPaginado(LocalDate dataVencimento, String descricao, int page, int size) {
    public FiltroPaginado(LocalDate dataVencimento, String descricao) {
        this(dataVencimento, descricao, 0, 10);
    }
}
