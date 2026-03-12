package com.itau.api_controle_financeiro.controllers;

import com.itau.api_controle_financeiro.dtos.LancamentoDto;
import com.itau.api_controle_financeiro.service.LancamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LancamentoController {

    private final LancamentoService lancamentoService;

    public LancamentoController(LancamentoService lancamentoService) {
        this.lancamentoService = lancamentoService;
    }

    @PostMapping("/lancamento")
    public ResponseEntity<Object> salvaLancamento(@RequestBody LancamentoDto lancamentoDto) {
        return this.lancamentoService.salvaLancamento(lancamentoDto);
    }


    @GetMapping("/lancamento")
    public ResponseEntity<Object> retornaLancamentos() {
        return this.lancamentoService.retornaLancamentos();
    }



    @GetMapping("/lancamento/{id_lancamento}")
    public ResponseEntity<Object> retornaLancamentoPeloId(@PathVariable Long id_lancamento) {
        return this.lancamentoService.retornaLancamentoPeloId(id_lancamento);
    }

    @DeleteMapping("/lancamento/{id_lancamento}")
    public ResponseEntity<Object> deletaLancamentoPeloId(@PathVariable Long id_lancamento){
        return this.lancamentoService.deletaLancamentoPeloId(id_lancamento);
    }


    @PutMapping("/lancamento/{id_lancamento}")
    public ResponseEntity<Object> atualizaLancamento(@PathVariable Long id_lancamento, @RequestBody LancamentoDto dto){
        return this.lancamentoService.atualizaLancamento(id_lancamento, dto);
    }

}
