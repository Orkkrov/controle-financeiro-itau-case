package com.itau.api_controle_financeiro.service;

import com.itau.api_controle_financeiro.dtos.BalancoDto;
import com.itau.api_controle_financeiro.dtos.CategoriaDto;
import com.itau.api_controle_financeiro.projection.BalancoProjection;
import com.itau.api_controle_financeiro.repository.LancamentoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BalancoService {

    private final LancamentoRepository lancamentoRepository;

    public BalancoService(LancamentoRepository lancamentoRepository) {
        this.lancamentoRepository = lancamentoRepository;
    }

    public BalancoDto consultarBalanco(LocalDate dataInicio, LocalDate dataFim, Long idCategoria) {

        BalancoProjection projection;

        if (idCategoria == null) {
            projection = lancamentoRepository.buscarBalanco(dataInicio, dataFim);
        } else {
            projection = lancamentoRepository.buscarBalancoComId(dataInicio, dataFim, idCategoria);
        }

        return new BalancoDto(
                new CategoriaDto(
                        projection.getIdCategoria(),
                        projection.getNomeCategoria()
                ),
                projection.getIdCategoria(),
                projection.getReceita(),
                projection.getDespesa(),
                projection.getReceita().subtract(projection.getDespesa())
        );
    }
}