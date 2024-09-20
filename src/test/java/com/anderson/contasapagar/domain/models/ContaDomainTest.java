package com.anderson.contasapagar.domain.models;

import com.anderson.contasapagar.domain.exceptions.CustomException;
import com.anderson.contasapagar.domain.factories.DataPagamentoFactory;
import com.anderson.contasapagar.domain.models.types.SituacaoPagamentoType;
import com.anderson.contasapagar.domain.vos.DataPagamento;
import com.anderson.contasapagar.domain.vos.DataVencimento;
import com.anderson.contasapagar.domain.vos.ValorMonetario;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ContaDomainTest {

    @Test
    void pagarSetsDataPagamentoAndSituacaoToPago() {
        ContaDomain conta = ContaDomain.builder().situacao(SituacaoPagamentoType.PENDENTE).build();
        conta.pagar();
        assertNotNull(conta.getDataPagamento());
        assertEquals(SituacaoPagamentoType.PAGO, conta.getSituacao());
    }

    @Test
    void pagarWithFutureDateThrowsException() {
        ContaDomain conta = ContaDomain.builder().situacao(SituacaoPagamentoType.PENDENTE).build();
        LocalDateTime futureDate = LocalDateTime.now().plusDays(1);
        assertThrows(CustomException.class, () -> conta.pagar(futureDate));
    }

    @Test
    void pagarWithValidDateSetsDataPagamentoAndSituacaoToPago() {
        ContaDomain conta = ContaDomain.builder().situacao(SituacaoPagamentoType.PENDENTE).build();
        LocalDateTime validDate = LocalDateTime.now();
        conta.pagar(validDate);
        assertEquals(validDate, conta.getDataPagamento().getDataPagamento());
        assertEquals(SituacaoPagamentoType.PAGO, conta.getSituacao());
    }

    @Test
    void reabrirSetsDataPagamentoToNullAndSituacaoToPendente() {
        ContaDomain conta = ContaDomain.builder()
                .dataPagamento(DataPagamentoFactory.create(LocalDateTime.now()))
                .situacao(SituacaoPagamentoType.PAGO)
                .build();
        conta.reabrir();
        assertNull(conta.getDataPagamento());
        assertEquals(SituacaoPagamentoType.PENDENTE, conta.getSituacao());
    }

    @Test
    void cancelarSetsSituacaoToCancelado() {
        ContaDomain conta = ContaDomain.builder().situacao(SituacaoPagamentoType.PENDENTE).build();
        conta.cancelar();
        assertEquals(SituacaoPagamentoType.CANCELADO, conta.getSituacao());
    }

    @Test
    void isPagoReturnsTrueWhenSituacaoIsPago() {
        ContaDomain conta = ContaDomain.builder().situacao(SituacaoPagamentoType.PAGO).build();
        assertTrue(conta.isPago());
    }

    @Test
    void isPagoReturnsFalseWhenSituacaoIsNotPago() {
        ContaDomain conta = ContaDomain.builder().situacao(SituacaoPagamentoType.PENDENTE).build();
        assertFalse(conta.isPago());
    }

    @Test
    void isPendenteReturnsTrueWhenSituacaoIsPendente() {
        ContaDomain conta = ContaDomain.builder().situacao(SituacaoPagamentoType.PENDENTE).build();
        assertTrue(conta.isPendente());
    }

    @Test
    void isPendenteReturnsFalseWhenSituacaoIsNotPendente() {
        ContaDomain conta = ContaDomain.builder().situacao(SituacaoPagamentoType.PAGO).build();
        assertFalse(conta.isPendente());
    }
}