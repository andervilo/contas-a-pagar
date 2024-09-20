package com.anderson.contasapagar.domain.factories;

import com.anderson.contasapagar.domain.exceptions.CustomException;
import com.anderson.contasapagar.domain.vos.DataPagamento;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DataPagamentoFactoryTest {

    @Test
    void createWithValidDate() {
        LocalDateTime now = LocalDateTime.now();
        DataPagamento dataPagamento = DataPagamentoFactory.create(now);
        assertEquals(now, dataPagamento.getDataPagamento());
    }


    @Test
    void createWithFutureDateThrowsException() {
        LocalDateTime now = LocalDateTime.now();
        assertThrows(CustomException.class, () -> DataPagamentoFactory.create(now.plusDays(2)));
    }
}