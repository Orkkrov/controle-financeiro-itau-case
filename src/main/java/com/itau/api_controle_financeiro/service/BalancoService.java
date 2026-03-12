package com.itau.api_controle_financeiro.service;


import com.itau.api_controle_financeiro.dtos.BalancoDto;
import com.itau.api_controle_financeiro.dtos.CategoriaDto;
import com.itau.api_controle_financeiro.projection.BalancoProjection;
import com.itau.api_controle_financeiro.repository.LancamentoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BalancoService {
    private final LancamentoRepository lancamentoRepository;

    public BalancoService(LancamentoRepository lancamentoRepository) {
        this.lancamentoRepository = lancamentoRepository;
    }

    public ResponseEntity<Object> consultarBalanco(LocalDate dataInicio, LocalDate dataFim, Long idCategoria) {

        if (idCategoria == null) {
            BalancoProjection b =   this.lancamentoRepository.buscarBalanco(dataInicio, dataFim);
            return new ResponseEntity<>(new BalancoDto(
                    new CategoriaDto(b.getIdCategoria(), b.getNomeCategoria()),
                    b.getIdCategoria(),
                    b.getReceita(),
                    b.getDespesa(),
                    b.getReceita().subtract(b.getDespesa())), HttpStatus.OK);

        }
        BalancoProjection b =   this.lancamentoRepository.buscarBalancoComId(dataInicio, dataFim, idCategoria);
        return new ResponseEntity<>(new BalancoDto(
                new CategoriaDto(b.getIdCategoria(), b.getNomeCategoria()),
                b.getIdCategoria(),
                b.getReceita(),
                b.getDespesa(),
                b.getReceita().subtract(b.getDespesa())), HttpStatus.OK);
    }
}
