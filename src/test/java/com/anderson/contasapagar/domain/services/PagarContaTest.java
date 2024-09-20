package com.anderson.contasapagar.domain.services;

import com.anderson.contasapagar.domain.exceptions.CustomException;
import com.anderson.contasapagar.domain.models.ContaDomain;
import com.anderson.contasapagar.domain.models.types.SituacaoPagamentoType;
import com.anderson.contasapagar.domain.repositories.ContaDomainRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PagarContaTest {

    private final ContaDomainRepository contaDomainRepository = mock(ContaDomainRepository.class);
    private final PagarConta pagarConta = new PagarConta(contaDomainRepository);

    @Test
    void executePaysContaSuccessfully() {
        ContaDomain conta = ContaDomain.builder().id(1L).situacao(SituacaoPagamentoType.PENDENTE).build();
        when(contaDomainRepository.findById(1L)).thenReturn(conta);

        ContaDomain paidConta = pagarConta.execute(1L);

        verify(contaDomainRepository, times(1)).save(conta);
    }

    @Test
    void executeWithAlreadyPaidContaThrowsException() {
        ContaDomain conta = ContaDomain.builder().id(1L).situacao(SituacaoPagamentoType.PAGO).build();
        when(contaDomainRepository.findById(1L)).thenReturn(conta);

        assertThrows(CustomException.class, () -> pagarConta.execute(1L));
        verify(contaDomainRepository, never()).save(any());
    }

    @Test
    void executeWithNonExistentContaThrowsException() {
        when(contaDomainRepository.findById(1L)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> pagarConta.execute(1L));
        verify(contaDomainRepository, never()).save(any());
    }
}