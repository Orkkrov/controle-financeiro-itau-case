package com.itau.api_controle_financeiro.controllers;


import com.itau.api_controle_financeiro.dtos.CategoriaDto;
import com.itau.api_controle_financeiro.entities.CategoriaEntity;
import com.itau.api_controle_financeiro.services.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }



    @PostMapping("/categoria")
    public ResponseEntity<Object> criaCategoria(@RequestBody CategoriaEntity categoriaEntity, @RequestHeader("api-key") String chaveApi){
        return this.categoriaService.salvaCategoria(categoriaEntity, chaveApi);
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<Object> retornaCategoriaPeloId(@PathVariable Long id, @RequestHeader(value = "api-key") String chaveApi){
        return this.categoriaService.retornaCategoriaPeloId(id, chaveApi);
    }


    @GetMapping("/categoria")
    public ResponseEntity<Object> retornaCategorias(@RequestHeader(value = "api-key") String chaveApi){
        return this.categoriaService.retornaCategorias(chaveApi);
    }

    @DeleteMapping("/categoria/{id}")
    public ResponseEntity<Object> criaCategoria(@PathVariable Long id, @RequestHeader(value = "api-key") String chaveApi){
        return this.categoriaService.deletaCategoriaPeloId(id, chaveApi);
    }

    @PutMapping("/categoria/{id}")
    public ResponseEntity<Object> atualizaCategoria(@PathVariable Long id, @RequestHeader(value = "api-key") String chaveApi, @RequestBody CategoriaEntity categoriaAtualizada){
       return this.categoriaService.atualizaCategoria(id, chaveApi, categoriaAtualizada);
    }



}
