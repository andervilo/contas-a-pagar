package com.anderson.contasapagar.domain.factories;

import com.anderson.contasapagar.domain.exceptions.CustomException;
import com.anderson.contasapagar.domain.vos.ValorMonetario;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValorMonetarioFactoryTest {

    @Test
    void createWithValidValue() {
        BigDecimal value = BigDecimal.valueOf(100.00);
        ValorMonetario valorMonetario = ValorMonetarioFactory.create(value);
        assertEquals(value, valorMonetario.getValor());
    }

    @Test
    void createWithNullValueThrowsException() {
        assertThrows(CustomException.class, () -> ValorMonetarioFactory.create(null));
    }

    @Test
    void createWithNegativeValueThrowsException() {
        BigDecimal negativeValue = BigDecimal.valueOf(-100.00);
        assertThrows(CustomException.class, () -> ValorMonetarioFactory.create(negativeValue));
    }

    @Test
    void createWithZeroValue() {
        BigDecimal zeroValue = BigDecimal.ZERO;
        assertThrows(CustomException.class, () -> ValorMonetarioFactory.create(zeroValue));
    }
}