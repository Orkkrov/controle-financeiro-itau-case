package com.itau.api_controle_financeiro.repositories;

import com.itau.api_controle_financeiro.entities.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {


    @Modifying
    @Transactional
    @Query(value = "UPDATE categoria SET nome = :nomeCategoria WHERE idCategoria = :id", nativeQuery = true)
    void atualizaCategoria(@Param("id")Long id, @Param("nomeCategoria") String nomeCategoria);
}
