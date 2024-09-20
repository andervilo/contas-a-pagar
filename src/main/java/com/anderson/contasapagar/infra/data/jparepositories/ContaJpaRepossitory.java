package com.anderson.contasapagar.infra.data.jparepositories;

import com.anderson.contasapagar.infra.data.entities.Conta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public interface ContaJpaRepossitory extends JpaRepository<Conta, Long> {

    @Query("SELECT SUM(c.valor) FROM Conta c WHERE c.dataPagamento BETWEEN :dataInicio AND :dataFim")
    BigDecimal totalPagoPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim);

    @Query(value="SELECT * FROM contas c " +
            "WHERE (?1 IS NULL OR DATE(c.data_vencimento) = DATE(?1)) " +
            "AND (?2 IS NULL OR UPPER(c.descricao) LIKE UPPER(?2))", nativeQuery = true)
    Page<Conta> buscarPorDataVencimentoEDescricao(
            String dataVencimento,
            String descricao,
            Pageable pageable);



    Page<Conta> findByDataVencimentoOrDescricaoContainingIgnoreCase(
            LocalDate dataVencimento,
            String descricao,
            Pageable pageable
    );
}
