package com.itau.api_controle_financeiro.controllers;

import com.itau.api_controle_financeiro.entity.CategoriaEntity;
import com.itau.api_controle_financeiro.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }



    @PostMapping
    public ResponseEntity<CategoriaEntity> criarCategoria(@RequestBody CategoriaEntity categoria) {

        CategoriaEntity novaCategoria = categoriaService.salvarCategoria(categoria);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(novaCategoria);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaEntity> buscarCategoria(@PathVariable Long id) {

        CategoriaEntity categoria = categoriaService.buscarCategoriaPorId(id);

        return ResponseEntity.ok(categoria);
    }

    @GetMapping
    public ResponseEntity<?> listarCategorias() {

        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {

        categoriaService.deletarCategoria(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaEntity> atualizarCategoria(
            @PathVariable Long id,
            @RequestBody CategoriaEntity categoria) {

        CategoriaEntity categoriaAtualizada = categoriaService.atualizarCategoria(id, categoria);

        return ResponseEntity.ok(categoriaAtualizada);
    }
}