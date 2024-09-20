package com.anderson.contasapagar.domain.services;

import com.anderson.contasapagar.domain.exceptions.CustomException;
import com.anderson.contasapagar.domain.repositories.ContaDomainRepository;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@RequiredArgsConstructor
public class ObterTotalPagoPorPeriodo {

    private final ContaDomainRepository contaDomainRepository;

    public BigDecimal execute(LocalDate dataInicio, LocalDate dataFim) {
        if (dataInicio == null || dataFim == null) {
            throw new CustomException(400,"Data de início e fim são obrigatórias");
        }
        if (dataInicio == null) {
            throw new CustomException(400,"Data de início é obrigatória");
        }
        if (dataFim == null) {
            throw new CustomException(400,"Data de fim é obrigatória");
        }
        return contaDomainRepository.sumByPeriodo(dataInicio, dataFim);
    }
}
