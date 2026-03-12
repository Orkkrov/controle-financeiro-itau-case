package com.itau.api_controle_financeiro.controllers;

import com.itau.api_controle_financeiro.dtos.SubCategoriaDto;
import com.itau.api_controle_financeiro.service.SubCategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subcategorias")
public class SubCategoriaController {

    private final SubCategoriaService subCategoriaService;

    public SubCategoriaController(SubCategoriaService subCategoriaService) {
        this.subCategoriaService = subCategoriaService;
    }

    @PostMapping
    public ResponseEntity<SubCategoriaDto> criarSubCategoria(@RequestBody SubCategoriaDto dto) {

        SubCategoriaDto subCategoria = subCategoriaService.salvarSubCategoria(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(subCategoria);
    }

    @GetMapping
    public ResponseEntity<List<SubCategoriaDto>> listarSubCategorias() {

        List<SubCategoriaDto> lista = subCategoriaService.listarSubCategorias();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubCategoriaDto> buscarSubCategoria(@PathVariable Long id) {

        SubCategoriaDto subCategoria = subCategoriaService.buscarSubCategoriaPorId(id);

        return ResponseEntity.ok(subCategoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSubCategoria(@PathVariable Long id) {

        subCategoriaService.deletarSubCategoria(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubCategoriaDto> atualizarSubCategoria(
            @PathVariable Long id,
            @RequestBody SubCategoriaDto dto) {

        SubCategoriaDto atualizado = subCategoriaService.atualizarSubCategoria(id, dto);

        return ResponseEntity.ok(atualizado);
    }
}