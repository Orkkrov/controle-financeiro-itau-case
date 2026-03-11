package com.itau.api_controle_financeiro.services;

import com.itau.api_controle_financeiro.dtos.ApiResposta;
import com.itau.api_controle_financeiro.dtos.CategoriaDto;
import com.itau.api_controle_financeiro.entities.CategoriaEntity;
import com.itau.api_controle_financeiro.repositories.CategoriaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {
    private static final Logger log =
            LoggerFactory.getLogger(CategoriaService.class);


    private final CategoriaRepository categoriaRepository;


    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public ResponseEntity<Object> salvaCategoria(CategoriaEntity categoriaEntity) {
        try {
            log.info("vai criar categoria com o nome {}" , categoriaEntity.getNome());
            categoriaEntity = this.categoriaRepository.save(categoriaEntity);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ApiResposta("erro_criacao", "categoria "+categoriaEntity.getNome()+" ja existe"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(categoriaEntity, HttpStatus.CREATED);
    }


    public ResponseEntity<Object> retornaCategorias(){

        log.info("vai retornar todas as categorias");
        return new ResponseEntity<>(this.categoriaRepository.findAll(), HttpStatus.OK) ;
    }


    public ResponseEntity<Object> retornaCategoriaPeloId(Long id) {

        log.info("vai retornar a categoria com o id{}" , id);
        Optional<CategoriaEntity> categoria = this.categoriaRepository.findById(id);

        if (categoria.isPresent()) return new ResponseEntity<>(categoria.get(), HttpStatus.OK);

        return  new ResponseEntity<>(new ApiResposta("erro_categoria", "id "+id+" de categoria nao existe"), HttpStatus.BAD_REQUEST);
    }



    public ResponseEntity<Object> deletaCategoriaPeloId(Long id) {
      try{
            log.info("vai deletar a categoria com o id{}" , id);
             this.categoriaRepository.deleteById(id);
             return new ResponseEntity<>(new ApiResposta("resposta" , "categoria excluida"), HttpStatus.OK);
        }catch (RuntimeException r) {
          r.printStackTrace();
            log.warn("categoria com o id{} nao existe" , id);
            return new ResponseEntity<>(new ApiResposta("erro_deletar","categoria nao existe" ) , HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object>  atualizaCategoria(Long id, CategoriaEntity categoriaAtualizada) {

        log.info("vai atualizar a categoria com o id {} com o nome {}" , id, categoriaAtualizada.getNome());
        Optional<CategoriaEntity> categoriaAntiga = this.categoriaRepository.findById(id);

        if (!categoriaAntiga.isPresent()) return new ResponseEntity<>(new ApiResposta("erro_codigo","id_categoria: " + id + " nao existe"),HttpStatus.NOT_FOUND);

        CategoriaEntity categoriaNova = new CategoriaEntity(categoriaAntiga.get().getIdCategoria(), categoriaAtualizada.getNome());

        this.categoriaRepository.atualizaCategoria(categoriaNova.getIdCategoria(), categoriaNova.getNome());
        return new ResponseEntity<>(categoriaNova,HttpStatus.OK);

    }
}
