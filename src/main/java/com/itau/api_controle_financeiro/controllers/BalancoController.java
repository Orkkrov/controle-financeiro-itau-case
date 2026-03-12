package com.itau.api_controle_financeiro.controllers;

import com.itau.api_controle_financeiro.dtos.BalancoDto;
import com.itau.api_controle_financeiro.service.BalancoService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/balancos")
public class BalancoController {

    private final BalancoService balancoService;

    public BalancoController(BalancoService balancoService) {
        this.balancoService = balancoService;
    }

    @GetMapping
    public ResponseEntity<BalancoDto> consultarBalanco(

            @RequestParam("data_inicio")
            @DateTimeFormat(pattern = "dd/MM/yyyy")
            LocalDate dataInicio,

            @RequestParam("data_fim")
            @DateTimeFormat(pattern = "dd/MM/yyyy")
            LocalDate dataFim,

            @RequestParam(value = "id_categoria", required = false)
            Long idCategoria
    ) {

        BalancoDto balanco = balancoService.consultarBalanco(
                dataInicio,
                dataFim,
                idCategoria
        );

        return ResponseEntity.ok(balanco);
    }
}