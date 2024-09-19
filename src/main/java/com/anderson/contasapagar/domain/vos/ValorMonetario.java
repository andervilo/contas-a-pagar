package com.anderson.contasapagar.domain.vos;

import com.anderson.contasapagar.domain.exceptions.CustomException;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
public class ValorMonetario {

    private final BigDecimal valor;

    public ValorMonetario(BigDecimal valor) {
        if (Objects.isNull(valor)) {
            throw new CustomException(400,"O valor não pode ser nulo.");
        }
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new CustomException(400,"O valor deve ser maior que zero.");
        }
        this.valor = valor;
    }

}

