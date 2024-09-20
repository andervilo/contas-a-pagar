package com.anderson.contasapagar.domain.services;

import com.anderson.contasapagar.domain.exceptions.CustomException;
import com.anderson.contasapagar.domain.models.ContaDomain;
import com.anderson.contasapagar.domain.models.types.SituacaoPagamentoType;
import com.anderson.contasapagar.domain.repositories.ContaDomainRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ImportarContaTest {

    private final ContaDomainRepository contaDomainRepository = mock(ContaDomainRepository.class);
    private final ImportarConta importarConta = new ImportarConta(contaDomainRepository);

    @Test
    void executeSavesContaSuccessfully() {
        ContaDomain conta = ContaDomain.builder().id(1L).build();
        when(contaDomainRepository.save(conta)).thenReturn(conta);

        ContaDomain savedConta = importarConta.execute(conta);

        assertEquals(conta, savedConta);
        verify(contaDomainRepository, times(1)).save(conta);
    }

    @Test
    void executeWithNullContaThrowsException() {
        assertThrows(CustomException.class, () -> importarConta.execute(null));
        verify(contaDomainRepository, never()).save(any());
    }

    @Test
    void executeWithExistingContaUpdatesSituacao() {
        ContaDomain conta = ContaDomain.builder().id(1L).situacao(SituacaoPagamentoType.PAGO).build();
        when(contaDomainRepository.save(conta)).thenReturn(conta);

        ContaDomain updatedConta = importarConta.execute(conta);

        assertEquals(SituacaoPagamentoType.PAGO, updatedConta.getSituacao());
        verify(contaDomainRepository, times(1)).save(conta);
    }
}