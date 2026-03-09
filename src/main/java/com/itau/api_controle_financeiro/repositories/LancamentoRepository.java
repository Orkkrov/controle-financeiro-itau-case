package com.itau.api_controle_financeiro.repositories;

import com.itau.api_controle_financeiro.entities.LancamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentoRepository extends JpaRepository<LancamentoEntity , Long> {
}
