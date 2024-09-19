package com.anderson.contasapagar.domain.services;

import com.anderson.contasapagar.domain.exceptions.CustomException;
import com.anderson.contasapagar.domain.models.ContaDomain;
import com.anderson.contasapagar.domain.repositories.ContaDomainRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PagarConta {

    private final ContaDomainRepository contaDomainRepository;

    public ContaDomain execute(Long id) {
        var conta = contaDomainRepository.findById(id);

        if (conta.isPago()) {
            throw new CustomException(400, "Conta já está paga");
        }

        conta.pagar();
        return contaDomainRepository.save(conta);
    }
}
