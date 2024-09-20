package com.anderson.contasapagar.domain.factories;


import com.anderson.contasapagar.domain.exceptions.CustomException;
import com.anderson.contasapagar.domain.vos.DataVencimento;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DataVencimentoFactoryTest {

    @Test
    void createWithValidDate() {
        LocalDate today = LocalDate.now();
        DataVencimento dataVencimento = DataVencimentoFactory.create(today);
        assertEquals(today, dataVencimento.getData());
    }

    @Test
    void createWithNullDateThrowsException() {
        assertThrows(CustomException.class, () -> DataVencimentoFactory.create(null));
    }

    @Test
    void createWithPastDate() {
        LocalDate pastDate = LocalDate.now().minusDays(1);
        DataVencimento dataVencimento = DataVencimentoFactory.create(pastDate);
        assertEquals(pastDate, dataVencimento.getData());
    }

    @Test
    void createWithFutureDate() {
        LocalDate futureDate = LocalDate.now().plusDays(1);
        DataVencimento dataVencimento = DataVencimentoFactory.create(futureDate);
        assertEquals(futureDate, dataVencimento.getData());
    }


}