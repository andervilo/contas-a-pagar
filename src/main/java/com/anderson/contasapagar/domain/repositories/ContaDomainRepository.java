package com.anderson.contasapagar.domain.repositories;

import com.anderson.contasapagar.domain.models.ContaDomain;
import com.anderson.contasapagar.domain.vos.FiltroPaginado;
import com.anderson.contasapagar.domain.vos.PageDomain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ContaDomainRepository {

    ContaDomain save(ContaDomain contaDomain);
    ContaDomain findById(Long id);
    ContaDomain update(ContaDomain contaDomain);
    PageDomain<ContaDomain> findAll(int pageNumber, int size);
    PageDomain<ContaDomain> findByDataVencimentoDescricao(FiltroPaginado filtroPaginado);
    BigDecimal sumByPeriodo(LocalDate dataInicio, LocalDate dataFim);
}
