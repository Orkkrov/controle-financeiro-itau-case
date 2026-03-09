package com.itau.api_controle_financeiro.services;

import com.itau.api_controle_financeiro.dtos.ApiResposta;
import com.itau.api_controle_financeiro.dtos.CategoriaDto;
import com.itau.api_controle_financeiro.dtos.ErrosDto;
import com.itau.api_controle_financeiro.entities.CategoriaEntity;
import com.itau.api_controle_financeiro.exceptions.CategoriaInexistenteException;
import com.itau.api_controle_financeiro.exceptions.TokenInvalidoException;
import com.itau.api_controle_financeiro.repositories.CategoriaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;


    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public ResponseEntity<Object> salvaCategoria(CategoriaEntity categoriaEntity, String chaveApi) {
        if (!tokenValido(chaveApi)) return new ResponseEntity<>(new ErrosDto("erro_validacao", "token invalido " + chaveApi), HttpStatus.UNAUTHORIZED);


        CategoriaDto categoriaDto = null;
        try {
            categoriaEntity = this.categoriaRepository.save(categoriaEntity);
            categoriaDto = new CategoriaDto(categoriaEntity.getId_categoria(), categoriaEntity.getNome());
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrosDto("erro_criacao", "categoria "+categoriaEntity.getNome()+" ja existe"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(categoriaDto, HttpStatus.CREATED);
    }


    public ResponseEntity<Object> retornaCategorias(String chaveApi){
        if (!tokenValido(chaveApi)) return new ResponseEntity<>(new ErrosDto("erro_validacao", "token invalido " + chaveApi), HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(this.categoriaRepository.findAll(), HttpStatus.OK) ;
    }


    public ResponseEntity<Object> retornaCategoriaPeloId(Long id, String chaveApi) {
        if (!tokenValido(chaveApi)) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        Optional<CategoriaEntity> categoria = this.categoriaRepository.findById(id);

        if (categoria.isPresent()) return new ResponseEntity<>(categoria, HttpStatus.OK);

        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    public boolean tokenValido(String chaveApi) {
        return chaveApi.equals("aXRhw7o=");
    }


    public ResponseEntity<Object> deletaCategoriaPeloId(Long id, String chaveApi) {
        if (!tokenValido(chaveApi)) return new ResponseEntity<>(new ErrosDto("erro_validacao", "token invalido " + chaveApi), HttpStatus.UNAUTHORIZED);
        try{
             this.categoriaRepository.deleteById(id);
             return new ResponseEntity<>(new ApiResposta("resposta" , "categoria excluida"), HttpStatus.OK);
        }catch (CategoriaInexistenteException c) {
            c.printStackTrace();
            return new ResponseEntity<>("CATEGORIA NAO EXISTE", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object>  atualizaCategoria(Long id, String chaveApi, CategoriaEntity categoriaAtualizada) {
        if (!tokenValido(chaveApi)) return new ResponseEntity<>(new ErrosDto("erro_validacao", "token invalido " + chaveApi), HttpStatus.UNAUTHORIZED);

        Optional<CategoriaEntity> categoriaAntiga = this.categoriaRepository.findById(id);

        if (!categoriaAntiga.isPresent()) return new ResponseEntity<>(new ErrosDto("erro_codigo","id_categoria: " + id + " nao existe"),HttpStatus.NOT_FOUND);

        CategoriaEntity categoriaNova = new CategoriaEntity(categoriaAntiga.get().getId_categoria(), categoriaAtualizada.getNome());

        this.categoriaRepository.atualizaCategoria(categoriaNova.getId_categoria(), categoriaNova.getNome());
        return new ResponseEntity<>(categoriaNova,HttpStatus.OK);

    }
}
