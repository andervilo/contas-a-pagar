package com.anderson.contasapagar.domain.services;

import com.anderson.contasapagar.domain.exceptions.CustomException;
import com.anderson.contasapagar.domain.models.ContaDomain;
import com.anderson.contasapagar.domain.repositories.ContaDomainRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CancelarConta {

    private final ContaDomainRepository contaDomainRepository;

    public ContaDomain execute(Long id) {
        var conta = contaDomainRepository.findById(id);

        if(conta.isPago()) {
            throw new CustomException(400, "Conta paga não pode ser cancelada");
        }

        conta.cancelar();
        return contaDomainRepository.save(conta);
    }
}
