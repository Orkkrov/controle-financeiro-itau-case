package com.itau.api_controle_financeiro.services;

import com.itau.api_controle_financeiro.dtos.CategoriaDto;
import com.itau.api_controle_financeiro.entities.CategoriaEntity;
import com.itau.api_controle_financeiro.exceptions.TokenInvalidoException;
import com.itau.api_controle_financeiro.repositories.CategoriaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;


    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public ResponseEntity<CategoriaDto> salvaCategoria(CategoriaEntity categoriaEntity, String chaveApi) {
        if (!tokenValido(chaveApi)) throw new TokenInvalidoException("token invalio " + chaveApi);

        CategoriaDto categoriaDto = null;
        try {
            categoriaEntity = this.categoriaRepository.save(categoriaEntity);
            categoriaDto = new CategoriaDto(categoriaEntity.getId_categoria(), categoriaEntity.getNome());
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(categoriaDto, HttpStatus.OK);
    }


    public List<CategoriaEntity> retornaCategorias(String chaveApi){
        if (!tokenValido(chaveApi)) throw new TokenInvalidoException("token invalio " + chaveApi);

        return this.categoriaRepository.findAll();
    }


    public boolean tokenValido(String chaveApi) {
        return chaveApi.equals("aXRhw7o=");
    }
}
