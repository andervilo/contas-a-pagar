package com.anderson.contasapagar.domain.services;

import com.anderson.contasapagar.domain.models.ContaDomain;
import com.anderson.contasapagar.domain.repositories.ContaDomainRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AtualizarConta {

    private final ContaDomainRepository contaDomainRepository;

    public ContaDomain execute(ContaDomain conta) {
        return contaDomainRepository.update(conta);
    }
}
