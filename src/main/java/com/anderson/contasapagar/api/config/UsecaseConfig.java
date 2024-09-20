package com.anderson.contasapagar.api.config;

import com.anderson.contasapagar.domain.repositories.ContaDomainRepository;
import com.anderson.contasapagar.domain.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UsecaseConfig {

    private final ContaDomainRepository contaDomainRepository;

    @Bean
    public CadastrarConta cadastrarConta() {
        return new CadastrarConta(contaDomainRepository);
    }

    @Bean
    public ImportarConta importarConta() {
        return new ImportarConta(contaDomainRepository);
    }

    @Bean
    public AtualizarConta buscarConta() {
        return new AtualizarConta(contaDomainRepository);
    }

    @Bean
    public AlterarSituacaoConta alterarSituacaoConta() {
        return new AlterarSituacaoConta(contaDomainRepository);
    }

    @Bean
    public ObterContaPorId obterContaPorId() {
        return new ObterContaPorId(contaDomainRepository);
    }

    @Bean
    public ObterContasPorFiltro obterContasPorFiltro() {
        return new ObterContasPorFiltro(contaDomainRepository);
    }

    @Bean
    public ObterTotalPagoPorPeriodo obterTotalPagoPorPeriodo() {
        return new ObterTotalPagoPorPeriodo(contaDomainRepository);
    }

    @Bean
    public PagarConta pagarConta() {
        return new PagarConta(contaDomainRepository);
    }

    @Bean
    public CancelarConta cancelarConta() {
        return new CancelarConta(contaDomainRepository);
    }

    @Bean
    public ReabrirConta reabrirConta() {
        return new ReabrirConta(contaDomainRepository);
    }

    @Bean
    public PagarContaPorData pagarContaPorData() {
        return new PagarContaPorData(contaDomainRepository);
    }
}
