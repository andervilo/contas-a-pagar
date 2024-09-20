package com.anderson.contasapagar.infra.adapter;

import com.anderson.contasapagar.domain.models.ContaDomain;
import com.anderson.contasapagar.domain.repositories.ContaDomainRepository;
import com.anderson.contasapagar.domain.vos.FiltroPaginado;
import com.anderson.contasapagar.domain.vos.PageDomain;
import com.anderson.contasapagar.infra.mapper.ContaInfraMapper;
import com.anderson.contasapagar.infra.data.entities.Conta;
import com.anderson.contasapagar.infra.data.jparepositories.ContaJpaRepossitory;
import com.anderson.contasapagar.infra.execeptions.ContaNotFoundExcetion;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ContaRepositoryAdapter implements ContaDomainRepository {

    private final ContaJpaRepossitory contaJpaRepossitory;
    private final ContaInfraMapper contaInfraMapper;

    @Override
    public ContaDomain save(ContaDomain contaDomain) {
        var conta = contaInfraMapper.toEntity(contaDomain);
        return contaInfraMapper.toDomain(contaJpaRepossitory.save(conta));
    }

    @Override
    public ContaDomain findById(Long id) {
        var conta = contaJpaRepossitory.findById(id).orElseThrow(ContaRepositoryAdapter::getContaNotFoundExcetion);
        return contaInfraMapper.toDomain(conta);
    }

    @Override
    public ContaDomain update(ContaDomain contaDomain) {
        var conta = contaInfraMapper.toEntity(contaDomain);
        var contaExistente = contaJpaRepossitory.findById(conta.getId()).orElseThrow(ContaRepositoryAdapter::getContaNotFoundExcetion);
        BeanUtils.copyProperties(conta, contaExistente, "id");
        return contaInfraMapper.toDomain(contaJpaRepossitory.save(contaExistente));
    }

    @Override
    public PageDomain<ContaDomain> findAll(int pageNumber, int size) {
        var page = contaJpaRepossitory.findAll(PageRequest.of(pageNumber, size));
        return getPageDomain(page);
    }

    @Override
    public PageDomain<ContaDomain> findByDataVencimentoDescricao(FiltroPaginado filtroPaginado) {
        var descricao = Objects.isNull(filtroPaginado.descricao()) || filtroPaginado.descricao().isEmpty() ? null :
                "%"+filtroPaginado.descricao().toUpperCase()+"%";
        var dataVencimento = Objects.isNull(filtroPaginado.dataVencimento()) ? null :
                filtroPaginado.dataVencimento().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        var pageable = PageRequest.of(filtroPaginado.page(), filtroPaginado.size());

        var page = contaJpaRepossitory.buscarPorDataVencimentoEDescricao(
                dataVencimento,
                descricao,
                pageable);
        return getPageDomain(page);
    }

    @Override
    public BigDecimal sumByPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        var valorPago = contaJpaRepossitory.totalPagoPorPeriodo(dataInicio.atStartOfDay(), dataFim.atTime(23, 59, 59));
        return Objects.isNull(valorPago) ? BigDecimal.ZERO : valorPago;
    }

    private PageDomain<ContaDomain> getPageDomain(Page<Conta> page) {
        return PageDomain.<ContaDomain>builder()
                .content(contaInfraMapper.toDomainList(page.getContent()))
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .size(page.getSize())
                .page(page.getNumber())
                .build();
    }

    private static ContaNotFoundExcetion getContaNotFoundExcetion() {
        return new ContaNotFoundExcetion(404, "Conta não encontrada!");
    }
}
