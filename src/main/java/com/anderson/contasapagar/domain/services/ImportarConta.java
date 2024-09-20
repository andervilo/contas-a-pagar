package com.anderson.contasapagar.domain.services;

import com.anderson.contasapagar.domain.models.ContaDomain;
import com.anderson.contasapagar.domain.models.types.SituacaoPagamentoType;
import com.anderson.contasapagar.domain.repositories.ContaDomainRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ImportarConta {

    private final ContaDomainRepository contaDomainRepository;

    public ContaDomain execute(ContaDomain contaDomain) {
        return contaDomainRepository.save(contaDomain);

    }
}
