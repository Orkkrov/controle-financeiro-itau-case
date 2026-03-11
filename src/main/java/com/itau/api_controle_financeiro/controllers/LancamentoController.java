package com.itau.api_controle_financeiro.controllers;

import com.itau.api_controle_financeiro.dtos.LancamentoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class LancamentoController {



    @PostMapping("/lancamento")
    public ResponseEntity<Object> salvaLancamento(@RequestBody LancamentoDto lancamentoDto) {
        return new ResponseEntity<>(lancamentoDto, HttpStatus.CREATED);
    }
}
