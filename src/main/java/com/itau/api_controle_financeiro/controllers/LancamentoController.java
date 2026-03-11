package com.itau.api_controle_financeiro.controllers;


import com.itau.api_controle_financeiro.dtos.ApiResposta;
import com.itau.api_controle_financeiro.dtos.LancamentoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class LancamentoController {


    @GetMapping("/lancamento")
    public HashMap<String, BigDecimal> testaLancamento(){
        HashMap<String, BigDecimal> valores = new HashMap<>();


        valores.put("valor",new BigDecimal( 20000));


        return valores;
    }



    @PostMapping("/lancamento")
    public ResponseEntity<Object> salvaLancamento(@RequestHeader("api-key") String chaveApi, @RequestBody LancamentoDto lancamentoDto) {
        if (!chaveApi.equals("")) return new ResponseEntity<>(new ApiResposta("erro_autorizacao", "chave: " +chaveApi+ " invalida"), HttpStatus.UNAUTHORIZED);


        return new ResponseEntity<>(lancamentoDto, HttpStatus.CREATED);
    }
}
