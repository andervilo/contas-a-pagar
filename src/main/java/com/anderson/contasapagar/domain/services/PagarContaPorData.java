package com.anderson.contasapagar.domain.services;

import com.anderson.contasapagar.domain.exceptions.CustomException;
import com.anderson.contasapagar.domain.models.ContaDomain;
import com.anderson.contasapagar.domain.repositories.ContaDomainRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class PagarContaPorData {

    private final ContaDomainRepository contaDomainRepository;

    public ContaDomain execute(Long id, LocalDateTime dataPagamento) {
        var conta = contaDomainRepository.findById(id);

        if (conta.isPago()) {
            throw new CustomException(400, "Conta já está paga");
        }

        if (dataPagamento.isAfter(LocalDateTime.now())) {
            throw new CustomException(400, "A data de pagamento não pode ser no futuro.");
        }

        conta.pagar(dataPagamento);
        return contaDomainRepository.save(conta);
    }
}
