package com.anderson.contasapagar.domain.vos;

import com.anderson.contasapagar.domain.exceptions.CustomException;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
public class DataVencimento {

    private final LocalDate data;

    public DataVencimento(LocalDate data) {
        if (data == null) {
            throw new CustomException(400,"A data de vencimento não pode ser nula.");
        }
        if (data.isBefore(LocalDate.now())) {
            throw new CustomException(400,"A data de vencimento não pode ser no passado.");
        }
        this.data = data;
    }

    public boolean isVencida() {
        return LocalDate.now().isAfter(data);
    }

    public String formatedDate() {
        DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(fomatter);
    }
}

