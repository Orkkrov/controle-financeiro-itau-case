package com.itau.api_controle_financeiro.controllers;

import com.itau.api_controle_financeiro.dtos.LancamentoDto;
import com.itau.api_controle_financeiro.service.LancamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

    private final LancamentoService lancamentoService;

    public LancamentoController(LancamentoService lancamentoService) {
        this.lancamentoService = lancamentoService;
    }

    @PostMapping
    public ResponseEntity<LancamentoDto> criarLancamento(@RequestBody LancamentoDto dto) {

        LancamentoDto lancamento = lancamentoService.salvarLancamento(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(lancamento);
    }

    @GetMapping
    public ResponseEntity<List<LancamentoDto>> listarLancamentos() {

        List<LancamentoDto> lancamentos = lancamentoService.listarLancamentos();

        return ResponseEntity.ok(lancamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LancamentoDto> buscarLancamento(@PathVariable Long id) {

        LancamentoDto lancamento = lancamentoService.buscarLancamentoPorId(id);

        return ResponseEntity.ok(lancamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLancamento(@PathVariable Long id) {

        lancamentoService.deletarLancamento(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<LancamentoDto> atualizarLancamento(
            @PathVariable Long id,
            @RequestBody LancamentoDto dto) {

        LancamentoDto atualizado = lancamentoService.atualizarLancamento(id, dto);

        return ResponseEntity.ok(atualizado);
    }
}