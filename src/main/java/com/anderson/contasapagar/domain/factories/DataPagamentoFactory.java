package com.anderson.contasapagar.domain.factories;

import com.anderson.contasapagar.domain.vos.DataPagamento;

import java.time.LocalDateTime;

public class DataPagamentoFactory {
    public static DataPagamento create(LocalDateTime dataPagamento) {
        return new DataPagamento(dataPagamento);
    }
}
