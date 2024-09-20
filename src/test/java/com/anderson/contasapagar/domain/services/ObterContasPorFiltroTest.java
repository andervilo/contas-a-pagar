package com.anderson.contasapagar.domain.services;

import com.anderson.contasapagar.domain.models.ContaDomain;
import com.anderson.contasapagar.domain.repositories.ContaDomainRepository;
import com.anderson.contasapagar.domain.vos.FiltroPaginado;
import com.anderson.contasapagar.domain.vos.PageDomain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ObterContasPorFiltroTest {

    private final ContaDomainRepository contaDomainRepository = mock(ContaDomainRepository.class);
    private final ObterContasPorFiltro obterContasPorFiltro = new ObterContasPorFiltro(contaDomainRepository);

    @Test
    void executeReturnsPageDomainWhenFiltroIsValid() {
        FiltroPaginado filtroPaginado = FiltroPaginado.builder().build();
        PageDomain<ContaDomain> expectedPage = PageDomain.<ContaDomain>builder().build();
        when(contaDomainRepository.findByDataVencimentoDescricao(filtroPaginado)).thenReturn(expectedPage);

        PageDomain<ContaDomain> result = obterContasPorFiltro.execute(filtroPaginado);

        assertEquals(expectedPage, result);
        verify(contaDomainRepository, times(1)).findByDataVencimentoDescricao(filtroPaginado);
    }

    @Test
    void executeReturnsEmptyPageWhenNoContasMatchFiltro() {
        FiltroPaginado filtroPaginado = FiltroPaginado.builder().build();
        PageDomain<ContaDomain> emptyPage = PageDomain.<ContaDomain>builder().build();
        when(contaDomainRepository.findByDataVencimentoDescricao(filtroPaginado)).thenReturn(emptyPage);

        PageDomain<ContaDomain> result = obterContasPorFiltro.execute(filtroPaginado);

        assertEquals(emptyPage, result);
        verify(contaDomainRepository, times(1)).findByDataVencimentoDescricao(filtroPaginado);
    }
}