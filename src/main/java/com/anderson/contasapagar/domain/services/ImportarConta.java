package com.anderson.contasapagar.domain.services;

import com.anderson.contasapagar.domain.exceptions.CustomException;
import com.anderson.contasapagar.domain.models.ContaDomain;
import com.anderson.contasapagar.domain.models.types.SituacaoPagamentoType;
import com.anderson.contasapagar.domain.repositories.ContaDomainRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ImportarConta {

    private final ContaDomainRepository contaDomainRepository;

    public ContaDomain execute(ContaDomain contaDomain) {
        if(contaDomain == null) {
            throw new CustomException(400,"Conta não pode ser nula");
        }
        return contaDomainRepository.save(contaDomain);

    }
}
