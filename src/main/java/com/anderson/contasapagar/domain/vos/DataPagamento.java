package com.anderson.contasapagar.domain.vos;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Getter
public class DataPagamento {

    private final LocalDateTime dataPagamento;

    public DataPagamento(LocalDateTime dataPagamento) {
        if (!Objects.isNull(dataPagamento) && dataPagamento.isAfter(LocalDateTime.now())) {
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
        LocalDateTime dataInicial = _dataInicial.atTime(0,0,0);
        LocalDateTime dataFinal = _dataFinal.atTime(23,59,59);
        return dataPagamento.isAfter(dataInicial) && dataPagamento.isBefore(dataFinal);
    }

    public String formattedDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dataPagamento.format(formatter);
    }

    public String formattedDate() {
        if(Objects.isNull(dataPagamento)) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataPagamento.format(formatter);
    }

}

