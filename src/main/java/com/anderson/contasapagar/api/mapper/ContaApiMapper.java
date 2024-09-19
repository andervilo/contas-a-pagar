package com.anderson.contasapagar.api.mapper;

import com.anderson.contasapagar.api.dto.ContaCreateRequest;
import com.anderson.contasapagar.api.dto.ContaRequest;
import com.anderson.contasapagar.api.dto.ContaResponse;
import com.anderson.contasapagar.domain.factories.DataPagamentoFactory;
import com.anderson.contasapagar.domain.factories.DataVencimentoFactory;
import com.anderson.contasapagar.domain.factories.ValorMonetarioFactory;
import com.anderson.contasapagar.domain.models.ContaDomain;
import com.anderson.contasapagar.domain.vos.DataPagamento;
import com.anderson.contasapagar.domain.vos.DataVencimento;
import com.anderson.contasapagar.domain.vos.ValorMonetario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface ContaApiMapper {

    @Mappings({
            @Mapping(target = "dataVencimento", source = "dataVencimento", qualifiedByName = "fromDataVencimentoVO"),
            @Mapping(target = "dataPagamento", source = "dataPagamento", qualifiedByName = "fromDataPagamentoVO"),
            @Mapping(target = "valor", source = "valor", qualifiedByName = "fromValorMonetarioVO")
    })
    ContaResponse toResponse(ContaDomain contaDomain);

    @Mappings({
            @Mapping(target = "dataVencimento", source = "dataVencimento", qualifiedByName = "toDataVencimentoVO"),
            @Mapping(target = "dataPagamento", source = "dataPagamento", qualifiedByName = "toDataPagamentoVO"),
            @Mapping(target = "valor", source = "valor", qualifiedByName = "toValorMonetarioVO")
    })
    ContaDomain toDomain(ContaRequest contaRequest);

    @Mappings({
            @Mapping(target = "dataVencimento", source = "dataVencimento", qualifiedByName = "toDataVencimentoVO"),
            @Mapping(target = "valor", source = "valor", qualifiedByName = "toValorMonetarioVO")
    })
    ContaDomain toDomain(ContaCreateRequest contaRequest);

    @Named("fromDataVencimentoVO")
    default String fromDataVencimentoVO(DataVencimento dataVencimento) {
        if (dataVencimento == null) {
            return null;
        }
        return dataVencimento.formatedDate();
    }

    @Named("fromDataPagamentoVO")
    default String fromDataPagamentoVO(DataPagamento dataPagamento) {
        if (dataPagamento == null) {
            return null;
        }
        return dataPagamento.formattedDate();
    }

    @Named("fromValorMonetarioVO")
    default BigDecimal fromValorMonetarioVO(ValorMonetario valor) {
        if (valor == null) {
            return null;
        }
        return valor.getValor();
    }

    @Named("toDataVencimentoVO")
    default DataVencimento toDataVencimentoVO(LocalDate dataVencimento) {
        if (dataVencimento == null) {
            return null;
        }
        return DataVencimentoFactory.create(dataVencimento);
    }

    @Named("toDataPagamentoVO")
    default DataPagamento toDataPagamentoVO(LocalDateTime dataPagamento) {
        if (dataPagamento == null) {
            return null;
        }
        return DataPagamentoFactory.create(dataPagamento);
    }

    @Named("toValorMonetarioVO")
    default ValorMonetario toValorMonetarioVO(BigDecimal valor) {
        if (valor == null) {
            return null;
        }
        return ValorMonetarioFactory.create(valor);
    }
}
