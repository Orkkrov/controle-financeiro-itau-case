package com.itau.api_controle_financeiro.repository;

import com.itau.api_controle_financeiro.entity.SubCategoriaEntity;
import com.itau.api_controle_financeiro.projection.SubCategoriaProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
public interface SubCategoriaRepository extends JpaRepository<SubCategoriaEntity, Long> {


    @Query(value = " SELECT id_subcategoria as idSubcategoria, nome, id_categoria as idCategoria FROM subcategoria WHERE id_subcategoria = :id", nativeQuery = true)
   Optional<SubCategoriaProjection> retornaSubCategoriaPeloID(@Param("id")Long id);



    @Query(value = " SELECT id_subcategoria as idSubcategoria, nome, id_categoria as idCategoria FROM subcategoria", nativeQuery = true)
    List<SubCategoriaProjection> retornaSubCategorias();



    @Modifying
    @Transactional
    @Query(value = "UPDATE subcategoria SET nome = :nomeSubCategoria WHERE id_subcategoria = :id", nativeQuery = true)
    void atualizaSubCategoria(@Param("id")Long id, @Param("nomeSubCategoria") String nomeSubCategoria);

    boolean existsByNome(String nome);
}
