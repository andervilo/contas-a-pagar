package com.anderson.contasapagar.domain.vos;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class DataPagamento {

    private final LocalDateTime dataPagamento;

    public DataPagamento(LocalDateTime dataPagamento) {
        if (dataPagamento.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("A data de pagamento não pode ser no futuro.");
        }
        this.dataPagamento = dataPagamento;
    }

    public boolean isBefore(LocalDateTime outraData) {
        return dataPagamento.isBefore(outraData);
    }

    public boolean isAfter(LocalDateTime outraData) {
        return dataPagamento.isAfter(outraData);
    }

    public boolean isWhithin(LocalDate _dataInicial, LocalDate _dataFinal) {
        LocalDateTime dataInicial = LocalDateTime.of(_dataInicial.getYear(), _dataFinal.getMonth(), _dataFinal.getDayOfMonth(), 0, 0, 0);
        LocalDateTime dataFinal = LocalDateTime.of(_dataFinal.getYear(), _dataFinal.getMonth(), _dataFinal.getDayOfMonth(), 23, 59, 59);
        return dataPagamento.isAfter(dataInicial) && dataPagamento.isBefore(dataFinal);
    }

    public String formattedDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dataPagamento.format(formatter);
    }

    public String formattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataPagamento.format(formatter);
    }

}

