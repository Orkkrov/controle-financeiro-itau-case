package com.itau.api_controle_financeiro.repositories;

import com.itau.api_controle_financeiro.entities.SubCategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface SubCategoriaRepository extends JpaRepository<SubCategoriaEntity, Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE subCategoria SET nome = :nomeSubCategoria WHERE idSubCategoria = :id", nativeQuery = true)
    void atualizaSubCategoria(@Param("id")Long id, @Param("nomeSubCategoria") String nomeSubCategoria);
}
