package com.itau.api_controle_financeiro.repository;

import com.itau.api_controle_financeiro.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {


}
