package com.anderson.contasapagar.infra.data.jparepositories;

import com.anderson.contasapagar.infra.data.entities.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaJpaRepossitory extends JpaRepository<Conta, Long> {
}
