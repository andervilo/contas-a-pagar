package com.anderson.contasapagar.domain.services;

import com.anderson.contasapagar.domain.models.ContaDomain;
import com.anderson.contasapagar.domain.models.types.SituacaoPagamentoType;
import com.anderson.contasapagar.domain.repositories.ContaDomainRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AlterarSituacaoContaTest {

    private final ContaDomainRepository contaDomainRepository = mock(ContaDomainRepository.class);
    private final AlterarSituacaoConta alterarSituacaoConta = new AlterarSituacaoConta(contaDomainRepository);

    @Test
    void executeChangesSituacaoToPago() {
        ContaDomain conta = ContaDomain.builder().situacao(SituacaoPagamentoType.PENDENTE).build();
        when(contaDomainRepository.findById(1L)).thenReturn(conta);

        alterarSituacaoConta.execute(1L, SituacaoPagamentoType.PAGO);

        assertEquals(SituacaoPagamentoType.PAGO, conta.getSituacao());
        verify(contaDomainRepository, times(1)).save(conta);
    }

    @Test
    void executeChangesSituacaoToCancelado() {
        ContaDomain conta = ContaDomain.builder().situacao(SituacaoPagamentoType.PENDENTE).build();
        when(contaDomainRepository.findById(1L)).thenReturn(conta);

        alterarSituacaoConta.execute(1L, SituacaoPagamentoType.CANCELADO);

        assertEquals(SituacaoPagamentoType.CANCELADO, conta.getSituacao());
        verify(contaDomainRepository, times(1)).save(conta);
    }

    @Test
    void executeWithNonExistentIdThrowsException() {
        when(contaDomainRepository.findById(1L)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> alterarSituacaoConta.execute(1L, SituacaoPagamentoType.PAGO));
        verify(contaDomainRepository, never()).save(any());
    }
}