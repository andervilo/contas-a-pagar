package com.anderson.contasapagar.domain.vos;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
public class ValorMonetario {

    private final BigDecimal valor;

    public ValorMonetario(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
        }
        this.valor = valor;
    }

}

