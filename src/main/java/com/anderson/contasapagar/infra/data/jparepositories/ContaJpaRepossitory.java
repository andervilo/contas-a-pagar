package com.anderson.contasapagar.infra.data.jparepositories;

import com.anderson.contasapagar.infra.data.entities.Conta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ContaJpaRepossitory extends JpaRepository<Conta, Long> {

    @Query("SELECT SUM(c.valor) FROM Conta c WHERE c.dataPagamento BETWEEN :dataInicio AND :dataFim")
    BigDecimal totalPagoPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim);

    @Query(
            "SELECT c FROM Conta c "+
            "WHERE (:dataVencimento IS NULL OR c.dataVencimento = :dataVencimento) "+
            "AND (:descricao IS NULL OR c.descricao LIKE %:descricao%)"
    )
    Page<Conta> buscarPorDataVencimentoEDescricao(LocalDate dataVencimento, String descricao, Pageable pageable);
}
