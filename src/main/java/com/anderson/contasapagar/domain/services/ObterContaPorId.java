package com.anderson.contasapagar.domain.services;

import com.anderson.contasapagar.domain.exceptions.CustomException;
import com.anderson.contasapagar.domain.models.ContaDomain;
import com.anderson.contasapagar.domain.repositories.ContaDomainRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ObterContaPorId {

    private final ContaDomainRepository contaDomainRepository;

    public ContaDomain execute(Long id) {
        if(id == null) {
            throw new CustomException(400,"Id não pode ser nulo");
        }

        var conta = contaDomainRepository.findById(id);

        if(conta == null) {
            throw new CustomException(404, "Conta não encontrada");
        }
        return conta;
    }
}
