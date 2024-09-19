package com.anderson.contasapagar.domain.services;

import com.anderson.contasapagar.domain.models.ContaDomain;
import com.anderson.contasapagar.domain.repositories.ContaDomainRepository;
import com.anderson.contasapagar.domain.vos.FiltroPaginado;
import com.anderson.contasapagar.domain.vos.PageDomain;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ObterContasPorFiltro {

    private final ContaDomainRepository contaDomainRepository;

    public PageDomain<ContaDomain> execute(FiltroPaginado filtroPaginado) {
        return contaDomainRepository.findByDataVencimentoDescricao(filtroPaginado);
    }
}
