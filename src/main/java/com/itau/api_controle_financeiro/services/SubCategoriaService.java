package com.itau.api_controle_financeiro.services;

import com.itau.api_controle_financeiro.dtos.ApiResposta;
import com.itau.api_controle_financeiro.dtos.SubCategoriaDto;
import com.itau.api_controle_financeiro.entities.CategoriaEntity;
import com.itau.api_controle_financeiro.entities.SubCategoriaEntity;
import com.itau.api_controle_financeiro.exception.CategoriaNaoExisteException;
import com.itau.api_controle_financeiro.repositories.SubCategoriaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubCategoriaService {
    private static final Logger log =
            LoggerFactory.getLogger(SubCategoriaService.class);



    private final SubCategoriaRepository subCategoriaRepository;


    public SubCategoriaService(SubCategoriaRepository subCategoriaRepository) {
        this.subCategoriaRepository = subCategoriaRepository;
    }

    public ResponseEntity<Object> salvaSubCategoria(SubCategoriaDto subCategoriaDto) {
        SubCategoriaEntity subCategoria = new SubCategoriaEntity();

        CategoriaEntity categoria = new CategoriaEntity();

        categoria.setIdCategoria(subCategoriaDto.getIdCategoria());

        subCategoria.setNome(subCategoriaDto.getNome());
        subCategoria.setIdCategoria(categoria);


        try{
            subCategoria = this.subCategoriaRepository.save(subCategoria);
        } catch (CategoriaNaoExisteException s) {
           log.error(s.getMessage());
            return new ResponseEntity<>(new ApiResposta("erro_categoria","categoria id "+subCategoriaDto.getIdCategoria()+" nao existe"),HttpStatus.BAD_REQUEST);
        }

        subCategoriaDto.setIdSubcategoria(subCategoria.getIdSubcategoria());

        return new ResponseEntity<>(subCategoriaDto,HttpStatus.CREATED);
    }

    public ResponseEntity<Object> retornaSubcategorias(){
        return new ResponseEntity<>(this.subCategoriaRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Object> retornaSubcategoriaPeloId(Long id){
        return new ResponseEntity<>(this.subCategoriaRepository.findById(id), HttpStatus.OK);
    }

    public ResponseEntity<Object> deletaSubCategoriaPeloId(Long id){
        try{
            this.subCategoriaRepository.deleteById(id);
            return new ResponseEntity<>(new ApiResposta("sucesso_delete","subCategoria id "+id+" deletada com sucesso"), HttpStatus.OK);
        } catch (RuntimeException e) {
            log.error("erro ao deletar subCategoria com o id");
            return new ResponseEntity<>(new ApiResposta("erro_delete","subCategoria id "+id+" nao encontrada"), HttpStatus.BAD_REQUEST);

        }
    }


    public ResponseEntity<Object>  atualizaSubCategoria(Long id,SubCategoriaEntity subcategoriaAtualizada) {

        log.info("vai atualizar a categoria com o id {} com o nome {}" , id, subcategoriaAtualizada.getNome());
        Optional<SubCategoriaEntity> subcategoriaAntiga = this.subCategoriaRepository.findById(id);

        if (!subcategoriaAntiga.isPresent()) return new ResponseEntity<>(new ApiResposta("erro_codigo","id_categoria: " + id + " nao existe"),HttpStatus.NOT_FOUND);

        SubCategoriaEntity subcategoriaNova = new SubCategoriaEntity(subcategoriaAntiga.get().getIdSubcategoria(), subcategoriaAtualizada.getNome());

        this.subCategoriaRepository.atualizaSubCategoria(subcategoriaNova.getIdSubcategoria(), subcategoriaNova.getNome());
        return new ResponseEntity<>(subcategoriaNova,HttpStatus.OK);

    }



}
