package com.itau.api_controle_financeiro.repository;

import com.itau.api_controle_financeiro.entity.LancamentoEntity;
import com.itau.api_controle_financeiro.projection.BalancoProjection;
import com.itau.api_controle_financeiro.projection.LancamentoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LancamentoRepository extends JpaRepository<LancamentoEntity, Long> {


    @Query(value = " SELECT id_lancamento AS idLancamento, valor  , data, id_subcategoria  as idSubcategoria , comentario FROM lancamento", nativeQuery = true)
    List<LancamentoProjection> retornaLancamentos();



    @Query(value =
            "SELECT " +
                    "c.id_categoria AS idCategoria, " +
                    "c.nome AS nomeCategoria, " +

                    "COALESCE(SUM(CASE " +
                    "WHEN l.valor > 0 THEN l.valor " +
                    "ELSE 0 END),0) AS receita, " +

                    "COALESCE(SUM(CASE " +
                    "WHEN l.valor < 0 THEN ABS(l.valor) " +
                    "ELSE 0 END),0) AS despesa " +

                    "FROM subcategoria s " +

                    "LEFT JOIN lancamento l " +
                    "ON s.id_subcategoria = l.id_subcategoria " +
                    "LEFT JOIN categoria c " +
                    "ON c.id_categoria = s.id_categoria " +

                    "WHERE  s.id_categoria = :idCategoria " +
                    "AND l.data BETWEEN :dataInicio AND :dataFim " +

                    "GROUP BY c.id_categoria, c.nome",
            nativeQuery = true)
    BalancoProjection buscarBalancoComId(
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim,
            @Param("idCategoria") Long idCategoria);


    @Query(value =
            "SELECT " +
                    "c.id_categoria AS idCategoria, " +
                    "c.nome AS nomeCategoria, " +

                    "COALESCE(SUM(CASE " +
                    "WHEN l.valor > 0 THEN l.valor " +
                    "ELSE 0 END),0) AS receita, " +

                    "COALESCE(SUM(CASE " +
                    "WHEN l.valor < 0 THEN ABS(l.valor) " +
                    "ELSE 0 END),0) AS despesa " +

                    "FROM subcategoria s " +

                    "LEFT JOIN lancamento l " +
                    "ON s.id_subcategoria = l.id_subcategoria " +
                    "WHERE l.data BETWEEN :dataInicio AND :dataFim " +

                    "LEFT JOIN categoria c " +
                    "ON c.id_categoria = s.id_categoria " +

                    "GROUP BY c.id_categoria, c.nome",
            nativeQuery = true)
    BalancoProjection buscarBalanco(
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim);




}

