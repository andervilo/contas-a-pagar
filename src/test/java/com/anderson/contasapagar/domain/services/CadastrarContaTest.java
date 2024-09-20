package com.anderson.contasapagar.domain.services;

import com.anderson.contasapagar.domain.models.ContaDomain;
import com.anderson.contasapagar.domain.models.types.SituacaoPagamentoType;
import com.anderson.contasapagar.domain.repositories.ContaDomainRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CadastrarContaTest {

    private final ContaDomainRepository contaDomainRepository = mock(ContaDomainRepository.class);
    private final CadastrarConta cadastrarConta = new CadastrarConta(contaDomainRepository);

    @Test
    void executeSetsSituacaoToPendenteAndSavesConta() {
        ContaDomain conta = ContaDomain.builder().build();
        when(contaDomainRepository.save(conta)).thenReturn(conta);

        ContaDomain savedConta = cadastrarConta.execute(conta);

        assertEquals(SituacaoPagamentoType.PENDENTE, savedConta.getSituacao());
        verify(contaDomainRepository, times(1)).save(conta);
    }

    @Test
    void executeWithNullContaThrowsException() {
        assertThrows(NullPointerException.class, () -> cadastrarConta.execute(null));
        verify(contaDomainRepository, never()).save(any());
    }

    @Test
    void executeWithExistingContaUpdatesSituacaoToPendente() {
        ContaDomain conta = ContaDomain.builder().situacao(SituacaoPagamentoType.PAGO).build();
        when(contaDomainRepository.save(conta)).thenReturn(conta);

        ContaDomain updatedConta = cadastrarConta.execute(conta);

        assertEquals(SituacaoPagamentoType.PENDENTE, updatedConta.getSituacao());
        verify(contaDomainRepository, times(1)).save(conta);
    }
}