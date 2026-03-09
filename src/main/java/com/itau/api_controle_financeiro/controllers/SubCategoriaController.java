package com.itau.api_controle_financeiro.controllers;

import com.itau.api_controle_financeiro.dtos.ApiResposta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/v1")
public class SubCategoriaController {
    @GetMapping("/subcategoria")
    public ResponseEntity<Object> retornaOK(){
        return new ResponseEntity<>(new ApiResposta("sucesso", "endpoint subcategoria"), HttpStatus.OK);
    }
}
