package com.anderson.contasapagar.domain.factories;

import com.anderson.contasapagar.domain.vos.ValorMonetario;

import java.math.BigDecimal;

public class ValorMonetarioFactory {
    public static ValorMonetario create(BigDecimal valor) {
        return new ValorMonetario(valor);
    }
}
