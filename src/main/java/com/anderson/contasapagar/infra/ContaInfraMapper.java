package com.anderson.contasapagar.infra;

import com.anderson.contasapagar.domain.models.ContaDomain;
import com.anderson.contasapagar.domain.vos.DataPagamento;
import com.anderson.contasapagar.domain.vos.DataVencimento;
import com.anderson.contasapagar.domain.vos.ValorMonetario;
import com.anderson.contasapagar.infra.data.entities.Conta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ContaInfraMapper {

    @Mappings({
        @Mapping(target = "dataVencimento", source = "dataVencimento", qualifiedByName = "toDataVencimentoVO"),
        @Mapping(target = "dataPagamento", source = "dataPagamento", qualifiedByName = "toDataPagamentoVO"),
        @Mapping(target = "valor", source = "valor", qualifiedByName = "toValorMonetarioVO")
    })
    ContaDomain toDomain(Conta conta);

    @Mappings({
        @Mapping(target = "dataVencimento", source = "dataVencimento", qualifiedByName = "fromDataVencimentoVO"),
        @Mapping(target = "dataPagamento", source = "dataPagamento", qualifiedByName = "fromDataPagamentoVO"),
        @Mapping(target = "valor", source = "valor", qualifiedByName = "fromValorMonetarioVO")
    })
    Conta toEntity(ContaDomain contaDomain);

    List<ContaDomain> toDomainList(List<Conta> contas);

    @Named("toDataVencimentoVO")
    default DataVencimento toDataVencimentoVO(LocalDate dataVencimento) {
        return new DataVencimento(dataVencimento);
    }

    @Named("toDataPagamentoVO")
    default DataPagamento toDataPagamentoVO(LocalDateTime dataPagamento) {
        return new DataPagamento(dataPagamento);
    }

    @Named("toValorMonetarioVO")
    default ValorMonetario toValorMonetarioVO(BigDecimal valor) {
        return new ValorMonetario(valor);
    }

    @Named("fromDataVencimentoVO")
    default LocalDate fromDataVencimentoVO(DataVencimento dataVencimento) {
        if(dataVencimento == null) {
            return null;
        }
        return dataVencimento.getData();
    }

    @Named("fromDataPagamentoVO")
    default LocalDateTime fromDataPagamentoVO(DataPagamento dataPagamento) {
        if(dataPagamento == null) {
            return null;
        }
        return dataPagamento.getDataPagamento();
    }

    @Named("fromValorMonetarioVO")
    default BigDecimal fromValorMonetarioVO(ValorMonetario valor) {
        if(valor == null) {
            return null;
        }
        return valor.getValor();
    }
}