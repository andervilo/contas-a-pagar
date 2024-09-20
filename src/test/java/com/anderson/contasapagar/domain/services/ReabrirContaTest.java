package com.anderson.contasapagar.domain.services;

import com.anderson.contasapagar.domain.exceptions.CustomException;
import com.anderson.contasapagar.domain.models.ContaDomain;
import com.anderson.contasapagar.domain.models.types.SituacaoPagamentoType;
import com.anderson.contasapagar.domain.repositories.ContaDomainRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ReabrirContaTest {

    private final ContaDomainRepository contaDomainRepository = mock(ContaDomainRepository.class);
    private final ReabrirConta reabrirConta = new ReabrirConta(contaDomainRepository);

    @Test
    void executeReopensContaSuccessfully() {
        ContaDomain conta = ContaDomain.builder().id(1L).situacao(SituacaoPagamentoType.PAGO).build();
        when(contaDomainRepository.findById(1L)).thenReturn(conta);

        ContaDomain reopenedConta = reabrirConta.execute(1L);

        verify(contaDomainRepository, times(1)).save(conta);
    }

    @Test
    void executeWithPendenteContaThrowsException() {
        ContaDomain conta = ContaDomain.builder().id(1L).situacao(SituacaoPagamentoType.PENDENTE).build();
        when(contaDomainRepository.findById(1L)).thenReturn(conta);

        assertThrows(CustomException.class, () -> reabrirConta.execute(1L));
        verify(contaDomainRepository, never()).save(any());
    }

    @Test
    void executeWithNonExistentContaThrowsException() {
        when(contaDomainRepository.findById(1L)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> reabrirConta.execute(1L));
        verify(contaDomainRepository, never()).save(any());
    }
}