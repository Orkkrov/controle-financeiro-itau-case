package com.itau.api_controle_financeiro.service;

import com.itau.api_controle_financeiro.dtos.ApiResposta;
import com.itau.api_controle_financeiro.dtos.SubCategoriaDto;
import com.itau.api_controle_financeiro.entity.CategoriaEntity;
import com.itau.api_controle_financeiro.entity.SubCategoriaEntity;
import com.itau.api_controle_financeiro.projection.SubCategoriaProjection;
import com.itau.api_controle_financeiro.repository.SubCategoriaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

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

        try {
            subCategoria = subCategoriaRepository.save(subCategoria);
        } catch (DataIntegrityViolationException e) {

            log.error(e.getMessage());
            if(!this.subCategoriaRepository.existsById(subCategoriaDto.getIdCategoria())){
                return new ResponseEntity<>(new ApiResposta("erro_requisicao","categoria nao existe"),HttpStatus.BAD_REQUEST);

            }

            if(subCategoriaRepository.existsByNome(subCategoriaDto.getNome())){
                return new ResponseEntity<>(new ApiResposta("erro_requisicao","subcategoria ja existe"),HttpStatus.BAD_REQUEST);
            }


        }


        subCategoriaDto.setIdSubcategoria(subCategoria.getIdSubcategoria());

        return new ResponseEntity<>(subCategoriaDto,HttpStatus.CREATED);
    }

    public ResponseEntity<Object> retornaSubcategorias(){
            return new ResponseEntity<>(this.subCategoriaRepository.retornaSubCategorias()
                    .stream()
                    .map(p -> new SubCategoriaDto(
                            p.getIdSubcategoria(),
                            p.getNome(),
                            p.getIdCategoria()))
                    .collect(Collectors.toList()), HttpStatus.OK);

    }

    public ResponseEntity<Object> retornaSubcategoriaPeloId(Long id){
        Optional<SubCategoriaProjection> subCategoriaProjection = this.subCategoriaRepository.retornaSubCategoriaPeloID(id);
        if (subCategoriaProjection.isPresent()) {
            SubCategoriaProjection s = subCategoriaProjection.get();

            return new ResponseEntity<>(new SubCategoriaDto(s.getIdSubcategoria(), s.getNome(), s.getIdCategoria()), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ApiResposta("erro_consulta", "subcategoria de id " +id+ " nao existe"), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<Object> deletaSubCategoriaPeloId(Long id){
        try{
            this.subCategoriaRepository.deleteById(id);
            return new ResponseEntity<>(new ApiResposta("sucesso_delete","subCategoria id "+id+" deletada com sucesso"), HttpStatus.OK);
        } catch (RuntimeException e) {
            log.error("erro ao deletar subcategoria com o id");
            return new ResponseEntity<>(new ApiResposta("erro_delete","subCategoria id "+id+" nao encontrada"), HttpStatus.BAD_REQUEST);

        }
    }


    public ResponseEntity<Object>  atualizaSubCategoria(Long id,SubCategoriaDto dto) {

        log.info("vai atualizar a categoria com o id {} com o nome {}" , id, dto.getNome());
        Optional<SubCategoriaEntity> subcategoriaAntiga = this.subCategoriaRepository.findById(id);

        if (!subcategoriaAntiga.isPresent()) return new ResponseEntity<>(new ApiResposta("erro_codigo","id_categoria: " + id + " nao existe"),HttpStatus.NOT_FOUND);

        SubCategoriaEntity subcategoriaNova = new SubCategoriaEntity(subcategoriaAntiga.get().getIdSubcategoria(), dto.getNome());


        dto.setIdCategoria(subcategoriaAntiga.get().getIdCategoria().getIdCategoria());
        dto.setIdSubcategoria(id);

        this.subCategoriaRepository.atualizaSubCategoria(dto.getIdSubcategoria(), dto.getNome());
        return new ResponseEntity<>(dto,HttpStatus.OK);

    }



}
