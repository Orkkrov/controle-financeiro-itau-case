package com.itau.api_controle_financeiro.controllers;

import com.itau.api_controle_financeiro.entity.CategoriaEntity;
import com.itau.api_controle_financeiro.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }



    @PostMapping("/categoria")
    public ResponseEntity<Object> criaCategoria(@RequestBody CategoriaEntity categoriaEntity){
        return this.categoriaService.salvaCategoria(categoriaEntity);
    }

    @GetMapping("/categoria/{id_categoria}")
    public ResponseEntity<Object> retornaCategoriaPeloId(@PathVariable Long id_categoria){
        return this.categoriaService.retornaCategoriaPeloId(id_categoria);
    }


    @GetMapping("/categoria")
    public ResponseEntity<Object> retornaCategorias(){
        return this.categoriaService.retornaCategorias();
    }

    @DeleteMapping("/categoria/{id_categoria}")
    public ResponseEntity<Object> criaCategoria(@PathVariable Long id_categoria){
        return this.categoriaService.deletaCategoriaPeloId(id_categoria);
    }

    @PutMapping("/categoria/{id_categoria}")
    public ResponseEntity<Object> atualizaCategoria(@PathVariable Long id_categoria, @RequestBody CategoriaEntity categoriaAtualizada){
       return this.categoriaService.atualizaCategoria(id_categoria,  categoriaAtualizada);
    }



}
