package com.anderson.contasapagar.domain.services;

import com.anderson.contasapagar.domain.repositories.ContaDomainRepository;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@RequiredArgsConstructor
public class ObterTotalPagoPorPeriodo {

    private final ContaDomainRepository contaDomainRepository;

    public BigDecimal execute(LocalDate dataInicio, LocalDate dataFim) {
        return contaDomainRepository.sumByPeriodo(dataInicio, dataFim);
    }
}
