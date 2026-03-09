package com.itau.api_controle_financeiro.repositories;

import com.itau.api_controle_financeiro.entities.SubCategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SubCategoriaRepository extends JpaRepository<SubCategoriaEntity, Long> {
}
