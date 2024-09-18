package com.anderson.contasapagar.domain.factories;

import com.anderson.contasapagar.domain.vos.DataVencimento;

import java.time.LocalDate;

public class DataVencimentoFactory {
    public static DataVencimento create(LocalDate dataVencimento) {
        return new DataVencimento(dataVencimento);
    }
}
