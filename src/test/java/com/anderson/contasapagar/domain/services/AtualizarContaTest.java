package com.anderson.contasapagar.domain.services;

import com.anderson.contasapagar.domain.models.ContaDomain;
import com.anderson.contasapagar.domain.repositories.ContaDomainRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AtualizarContaTest {

    private final ContaDomainRepository contaDomainRepository = mock(ContaDomainRepository.class);
    private final AtualizarConta atualizarConta = new AtualizarConta(contaDomainRepository);

    @Test
    void executeUpdatesContaSuccessfully() {
        ContaDomain conta = ContaDomain.builder().id(1L).build();
        when(contaDomainRepository.update(conta)).thenReturn(conta);

        ContaDomain updatedConta = atualizarConta.execute(conta);

        assertEquals(conta, updatedConta);
        verify(contaDomainRepository, times(1)).update(conta);
    }

    @Test
    void executeWithNonExistentContaThrowsException() {
        ContaDomain conta = ContaDomain.builder().id(1L).build();
        when(contaDomainRepository.update(conta)).thenThrow(new RuntimeException("Conta not found"));

        assertThrows(RuntimeException.class, () -> atualizarConta.execute(conta));
        verify(contaDomainRepository, times(1)).update(conta);
    }
}