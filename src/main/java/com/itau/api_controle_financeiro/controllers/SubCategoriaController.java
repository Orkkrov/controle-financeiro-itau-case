package com.itau.api_controle_financeiro.controllers;

import com.itau.api_controle_financeiro.dtos.ApiResposta;
import com.itau.api_controle_financeiro.dtos.SubCategoriaDto;
import com.itau.api_controle_financeiro.entities.CategoriaEntity;
import com.itau.api_controle_financeiro.entities.SubCategoriaEntity;
import com.itau.api_controle_financeiro.services.SubCategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SubCategoriaController {
    private final SubCategoriaService subCategoriaService;

    public SubCategoriaController(SubCategoriaService subCategoriaService) {
        this.subCategoriaService = subCategoriaService;
    }



    @PostMapping("/subcategoria")
    public  ResponseEntity<Object> salvaSubcategoria(@RequestBody SubCategoriaDto subCategoriaDto){
        return this.subCategoriaService.salvaSubCategoria(subCategoriaDto);
    }

    @GetMapping("/subcategoria")
    public  ResponseEntity<Object> retornaSubcategorias(){
        return this.subCategoriaService.retornaSubcategorias();
    }

    @GetMapping("/subcategoria/{id_Subcategoria}")
    public  ResponseEntity<Object> retornaSubcategoriaPeloId(@PathVariable Long id_Subcategoria){
        return this.subCategoriaService.retornaSubcategoriaPeloId(id_Subcategoria);
    }

    @DeleteMapping("/subcategoria/{id_Subcategoria}")
    public ResponseEntity<Object> deletaSubCategoriaPeloId(@PathVariable Long id_Subcategoria){
        return this.subCategoriaService.deletaSubCategoriaPeloId(id_Subcategoria);
    }


    @PutMapping("/subcategoria/{id_Subcategoria}")
    public ResponseEntity<Object> atualizaCategoria(@PathVariable Long id_subcategoria, @RequestBody SubCategoriaEntity subCategoriaEntity){
        return this.subCategoriaService.atualizaSubCategoria(id_subcategoria,  subCategoriaEntity);
    }

}
