package com.anderson.contasapagar.domain.services;

import com.anderson.contasapagar.domain.models.types.SituacaoPagamentoType;
import com.anderson.contasapagar.domain.repositories.ContaDomainRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AlterarSituacaoConta {

    private final ContaDomainRepository contaDomainRepository;

    public void execute(Long id, SituacaoPagamentoType situacao) {
        var conta = contaDomainRepository.findById(id);
        conta.setSituacao(situacao);
        contaDomainRepository.save(conta);
    }
}
