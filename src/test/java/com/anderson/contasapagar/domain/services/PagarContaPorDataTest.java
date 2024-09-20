package com.anderson.contasapagar.domain.services;

import com.anderson.contasapagar.domain.exceptions.CustomException;
import com.anderson.contasapagar.domain.models.ContaDomain;
import com.anderson.contasapagar.domain.models.types.SituacaoPagamentoType;
import com.anderson.contasapagar.domain.repositories.ContaDomainRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PagarContaPorDataTest {

    private final ContaDomainRepository contaDomainRepository = mock(ContaDomainRepository.class);
    private final PagarContaPorData pagarContaPorData = new PagarContaPorData(contaDomainRepository);

    @Test
    void executePaysContaSuccessfully() {
        ContaDomain conta = ContaDomain.builder().id(1L).situacao(SituacaoPagamentoType.PENDENTE).build();
        LocalDateTime dataPagamento = LocalDateTime.now().minusDays(1);
        when(contaDomainRepository.findById(1L)).thenReturn(conta);

        ContaDomain paidConta = pagarContaPorData.execute(1L, dataPagamento);

        verify(contaDomainRepository, times(1)).save(conta);
    }

    @Test
    void executeWithAlreadyPaidContaThrowsException() {
        ContaDomain conta = ContaDomain.builder().id(1L).situacao(SituacaoPagamentoType.PAGO).build();
        LocalDateTime dataPagamento = LocalDateTime.now().minusDays(1);
        when(contaDomainRepository.findById(1L)).thenReturn(conta);

        assertThrows(CustomException.class, () -> pagarContaPorData.execute(1L, dataPagamento));
        verify(contaDomainRepository, never()).save(any());
    }

    @Test
    void executeWithFutureDataPagamentoThrowsException() {
        ContaDomain conta = ContaDomain.builder().id(1L).situacao(SituacaoPagamentoType.PENDENTE).build();
        LocalDateTime dataPagamento = LocalDateTime.now().plusDays(1);
        when(contaDomainRepository.findById(1L)).thenReturn(conta);

        assertThrows(CustomException.class, () -> pagarContaPorData.execute(1L, dataPagamento));
        verify(contaDomainRepository, never()).save(any());
    }

    @Test
    void executeWithNonExistentContaThrowsException() {
        LocalDateTime dataPagamento = LocalDateTime.now().minusDays(1);
        when(contaDomainRepository.findById(1L)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> pagarContaPorData.execute(1L, dataPagamento));
        verify(contaDomainRepository, never()).save(any());
    }
}