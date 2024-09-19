package com.anderson.contasapagar.domain.services;

import com.anderson.contasapagar.domain.models.ContaDomain;
import com.anderson.contasapagar.domain.models.types.SituacaoPagamentoType;
import com.anderson.contasapagar.domain.repositories.ContaDomainRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CadastrarConta {

    private final ContaDomainRepository contaDomainRepository;

    public ContaDomain execute(ContaDomain contaDomain) {
        contaDomain.setSituacao(SituacaoPagamentoType.PENDENTE);
        return contaDomainRepository.save(contaDomain);

    }
}
