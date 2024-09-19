package com.anderson.contasapagar.domain.services;

import com.anderson.contasapagar.domain.models.ContaDomain;
import com.anderson.contasapagar.domain.repositories.ContaDomainRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ObterContaPorId {

    private final ContaDomainRepository contaDomainRepository;

    public ContaDomain execute(Long id) {
        return contaDomainRepository.findById(id);
    }
}
