package com.itau.api_controle_financeiro.controllers;

import com.itau.api_controle_financeiro.service.BalancoService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class BalancoController {

    private final BalancoService balancoService;


    public BalancoController(BalancoService balancoService) {
        this.balancoService = balancoService;
    }

    @GetMapping("/balanco")
    public ResponseEntity<Object> consultarBalanco(

            @RequestParam("data_inicio")
            @DateTimeFormat(pattern = "dd/MM/yyyy")
            LocalDate dataInicio,

            @RequestParam("data_fim")
            @DateTimeFormat(pattern = "dd/MM/yyyy")
            LocalDate dataFim,

            @RequestParam(value = "id_categoria", required = false)
            Long idCategoria
    ) {

        return  this.balancoService.consultarBalanco(dataInicio,dataFim,idCategoria );
    }
}
