package com.anderson.contasapagar.domain.services;

import com.anderson.contasapagar.domain.exceptions.CustomException;
import com.anderson.contasapagar.domain.models.ContaDomain;
import com.anderson.contasapagar.domain.repositories.ContaDomainRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ObterContaPorIdTest {

    private final ContaDomainRepository contaDomainRepository = mock(ContaDomainRepository.class);
    private final ObterContaPorId obterContaPorId = new ObterContaPorId(contaDomainRepository);

    @Test
    void executeReturnsContaWhenIdExists() {
        ContaDomain conta = ContaDomain.builder().id(1L).build();
        when(contaDomainRepository.findById(1L)).thenReturn(conta);

        ContaDomain result = obterContaPorId.execute(1L);

        assertEquals(conta, result);
        verify(contaDomainRepository, times(1)).findById(1L);
    }

    @Test
    void executeThrowsExceptionWhenIdDoesNotExist() {
        when(contaDomainRepository.findById(1L)).thenReturn(null);

        assertThrows(CustomException.class, () -> obterContaPorId.execute(1L));
        verify(contaDomainRepository, times(1)).findById(1L);
    }

    @Test
    void executeThrowsExceptionWhenIdIsNull() {
        assertThrows(CustomException.class, () -> obterContaPorId.execute(null));
        verify(contaDomainRepository, never()).findById(any());
    }
}