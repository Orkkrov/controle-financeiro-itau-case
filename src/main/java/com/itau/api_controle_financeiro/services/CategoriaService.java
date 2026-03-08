package com.itau.api_controle_financeiro.services;

import com.itau.api_controle_financeiro.dtos.CategoriaDto;
import com.itau.api_controle_financeiro.entities.CategoriaEntity;
import com.itau.api_controle_financeiro.repositories.CategoriaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;


    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public CategoriaDto salvaCategoria(CategoriaEntity categoriaEntity){
        categoriaEntity = this.categoriaRepository.save(categoriaEntity);

        return new CategoriaDto(categoriaEntity.getId_categoria(), categoriaEntity.getNome());
    }

}
