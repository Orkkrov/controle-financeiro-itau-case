package com.itau.api_controle_financeiro.controllers;


import com.itau.api_controle_financeiro.dtos.CategoriaDto;
import com.itau.api_controle_financeiro.entities.CategoriaEntity;
import com.itau.api_controle_financeiro.services.CategoriaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/teste")
    public String teste(){
        return "rodando";
    }



    @PostMapping("/categoria")
    public CategoriaDto criaCategoria(@RequestBody CategoriaEntity categoriaEntity){

        return this.categoriaService.salvaCategoria(categoriaEntity);
    }


}
