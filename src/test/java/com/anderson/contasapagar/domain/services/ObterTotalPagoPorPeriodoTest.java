package com.anderson.contasapagar.domain.services;

import com.anderson.contasapagar.domain.exceptions.CustomException;
import com.anderson.contasapagar.domain.repositories.ContaDomainRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ObterTotalPagoPorPeriodoTest {

    private final ContaDomainRepository contaDomainRepository = mock(ContaDomainRepository.class);
    private final ObterTotalPagoPorPeriodo obterTotalPagoPorPeriodo = new ObterTotalPagoPorPeriodo(contaDomainRepository);

    @Test
    void executeReturnsCorrectSumForGivenPeriod() {
        LocalDate dataInicio = LocalDate.of(2023, 1, 1);
        LocalDate dataFim = LocalDate.of(2023, 1, 31);
        BigDecimal expectedSum = new BigDecimal("1000.00");
        when(contaDomainRepository.sumByPeriodo(dataInicio, dataFim)).thenReturn(expectedSum);

        BigDecimal result = obterTotalPagoPorPeriodo.execute(dataInicio, dataFim);

        assertEquals(expectedSum, result);
        verify(contaDomainRepository, times(1)).sumByPeriodo(dataInicio, dataFim);
    }

    @Test
    void executeReturnsZeroWhenNoPaymentsInPeriod() {
        LocalDate dataInicio = LocalDate.of(2023, 1, 1);
        LocalDate dataFim = LocalDate.of(2023, 1, 31);
        BigDecimal expectedSum = BigDecimal.ZERO;
        when(contaDomainRepository.sumByPeriodo(dataInicio, dataFim)).thenReturn(expectedSum);

        BigDecimal result = obterTotalPagoPorPeriodo.execute(dataInicio, dataFim);

        assertEquals(expectedSum, result);
        verify(contaDomainRepository, times(1)).sumByPeriodo(dataInicio, dataFim);
    }

    @Test
    void executeThrowsExceptionWhenDataInicioIsNull() {
        LocalDate dataFim = LocalDate.of(2023, 1, 31);

        assertThrows(CustomException.class, () -> obterTotalPagoPorPeriodo.execute(null, dataFim));
        verify(contaDomainRepository, never()).sumByPeriodo(any(), any());
    }

    @Test
    void executeThrowsExceptionWhenDataFimIsNull() {
        LocalDate dataInicio = LocalDate.of(2023, 1, 1);

        assertThrows(CustomException.class, () -> obterTotalPagoPorPeriodo.execute(dataInicio, null));
        verify(contaDomainRepository, never()).sumByPeriodo(any(), any());
    }

    @Test
    void executeThrowsExceptionWhenDataInicioAndDataFimAreNull() {
        assertThrows(CustomException.class, () -> obterTotalPagoPorPeriodo.execute(null, null));
        verify(contaDomainRepository, never()).sumByPeriodo(any(), any());
    }
}