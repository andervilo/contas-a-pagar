package com.anderson.contasapagar.infra.data.entities;

import com.anderson.contasapagar.domain.models.types.SituacaoPagamentoType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "contas")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;

    private LocalDateTime dataPagamento;
    private BigDecimal valor;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private SituacaoPagamentoType situacao;
}
