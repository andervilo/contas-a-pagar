package com.anderson.contasapagar.domain.models;

import com.anderson.contasapagar.domain.exceptions.CustomException;
import com.anderson.contasapagar.domain.factories.DataPagamentoFactory;
import com.anderson.contasapagar.domain.models.types.SituacaoPagamentoType;
import com.anderson.contasapagar.domain.vos.DataPagamento;
import com.anderson.contasapagar.domain.vos.DataVencimento;
import com.anderson.contasapagar.domain.vos.ValorMonetario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaDomain {
    private Long id;
    private DataVencimento dataVencimento;
    private DataPagamento dataPagamento;
    private ValorMonetario valor;
    private String descricao;
    private SituacaoPagamentoType situacao;

    public boolean isPago() {
        return this.situacao == SituacaoPagamentoType.PAGO;
    }

    public boolean isPendente() {
        return this.situacao == SituacaoPagamentoType.PENDENTE;
    }

    public void pagar() {
        this.dataPagamento = DataPagamentoFactory.create(LocalDateTime.now());
        this.situacao = SituacaoPagamentoType.PAGO;
    }

    public void  pagar(LocalDateTime dataPagamento) {
        if (dataPagamento.isAfter(LocalDateTime.now())) {
            throw new CustomException(400,"A data de pagamento não pode ser no futuro.");
        }
        this.dataPagamento = DataPagamentoFactory.create(dataPagamento);
        this.situacao = SituacaoPagamentoType.PAGO;
    }

    public void reabrir() {
        this.dataPagamento = null;
        this.situacao = SituacaoPagamentoType.PENDENTE;
    }

    public void cancelar() {
        this.situacao = SituacaoPagamentoType.CANCELADO;
    }
}
