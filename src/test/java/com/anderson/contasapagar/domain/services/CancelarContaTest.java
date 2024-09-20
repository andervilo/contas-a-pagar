package com.anderson.contasapagar.domain.services;

import com.anderson.contasapagar.domain.exceptions.CustomException;
import com.anderson.contasapagar.domain.models.ContaDomain;
import com.anderson.contasapagar.domain.models.types.SituacaoPagamentoType;
import com.anderson.contasapagar.domain.repositories.ContaDomainRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CancelarContaTest {

    private final ContaDomainRepository contaDomainRepository = mock(ContaDomainRepository.class);
    private final CancelarConta cancelarConta = new CancelarConta(contaDomainRepository);

    @Test
    void executeCancelsContaSuccessfully() {
        ContaDomain conta = ContaDomain.builder().id(1L).situacao(SituacaoPagamentoType.PENDENTE).build();
        when(contaDomainRepository.findById(1L)).thenReturn(conta);

        ContaDomain canceledConta = cancelarConta.execute(1L);

        verify(contaDomainRepository, times(1)).save(conta);
    }

    @Test
    void executeWithPaidContaThrowsException() {
        ContaDomain conta = ContaDomain.builder().id(1L).situacao(SituacaoPagamentoType.PAGO).build();
        when(contaDomainRepository.findById(1L)).thenReturn(conta);

        assertThrows(CustomException.class, () -> cancelarConta.execute(1L));
        verify(contaDomainRepository, never()).save(any());
    }

    @Test
    void executeWithNonExistentContaThrowsException() {
        when(contaDomainRepository.findById(1L)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> cancelarConta.execute(1L));
        verify(contaDomainRepository, never()).save(any());
    }
}