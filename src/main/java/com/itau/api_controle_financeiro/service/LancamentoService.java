package com.itau.api_controle_financeiro.service;


import com.itau.api_controle_financeiro.dtos.ApiResposta;
import com.itau.api_controle_financeiro.dtos.LancamentoDto;
import com.itau.api_controle_financeiro.entity.LancamentoEntity;
import com.itau.api_controle_financeiro.entity.SubCategoriaEntity;
import com.itau.api_controle_financeiro.repository.LancamentoRepository;
import com.itau.api_controle_financeiro.repository.SubCategoriaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LancamentoService {
    private static final Logger log =
            LoggerFactory.getLogger(LancamentoService.class);

    private final LancamentoRepository lancamentoRepository;
    private final SubCategoriaRepository subCategoriaRepository;

    public LancamentoService(LancamentoRepository lancamentoRepository, SubCategoriaRepository subCategoriaRepository) {
        this.lancamentoRepository = lancamentoRepository;
        this.subCategoriaRepository = subCategoriaRepository;
    }

    public ResponseEntity<Object> salvaLancamento(LancamentoDto dto) {

        if (dto.getValor().doubleValue() == 0.0)  return new ResponseEntity<>(new ApiResposta("erro_requisicao", "valor deve ser diferente de zero"), HttpStatus.BAD_REQUEST);


        Optional<SubCategoriaEntity> subCategoria = this.subCategoriaRepository.findById(dto.getId_subcategoria());
       if (!subCategoria.isPresent()) return new ResponseEntity<>(new ApiResposta("erro_requisicao", "subcategoria "+dto.getId_subcategoria()+" nao existe"), HttpStatus.BAD_REQUEST);


        LancamentoEntity lancamento = new LancamentoEntity(
                dto.getValor(),
                dto.getData(),
                subCategoria.get(),
                dto.getComentario()
        );
        lancamento = this.lancamentoRepository.save(lancamento);
        dto.setId_lancamento(lancamento.getIdLancamento());

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    public ResponseEntity<Object> retornaLancamentos() {
        return new ResponseEntity<>(this.lancamentoRepository.retornaLancamentos()
                .stream()
                .map(l -> new LancamentoDto(
                     l.getIdLancamento(),
                        l.getValor(),
                        l.getData(),
                        l.getIdSubcategoria(),
                        l.getComentario()

                ))
                .collect(Collectors.toList()), HttpStatus.OK);

    }


    public ResponseEntity<Object> retornaLancamentoPeloId(Long idLancamento) {
        Optional<LancamentoEntity> lancamento = this.lancamentoRepository.findById(idLancamento);
        if (!lancamento.isPresent()) return new ResponseEntity<>(new ApiResposta("erro_requisicao", "lancamento " +idLancamento+" nao existe"), HttpStatus.BAD_REQUEST);

        LancamentoDto dto = new LancamentoDto(
                lancamento.get().getIdLancamento(),
                lancamento.get().getValor(),
                lancamento.get().getData(),
                lancamento.get().getSubCategoria().getIdSubcategoria(),
                lancamento.get().getComentario()
                );

        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

    public ResponseEntity<Object> deletaLancamentoPeloId(Long idLancamento) {
        try{
            log.info("vai deletar o lancamento com o id{}" , idLancamento);
            this.lancamentoRepository.deleteById(idLancamento);
            return new ResponseEntity<>(new ApiResposta("resposta" , "lancamento excluido"), HttpStatus.OK);
        }catch (RuntimeException r) {
            r.printStackTrace();
            log.warn("lancamento com o id{} nao existe" , idLancamento);
            return new ResponseEntity<>(new ApiResposta("erro_deletar","lancamento nao existe" ) , HttpStatus.BAD_REQUEST);
        }
    }



    public ResponseEntity<Object>  atualizaLancamento(Long id,LancamentoDto dto) {

        log.info("vai atualizar o lancamento de id {}" , id);
        Optional<LancamentoEntity> lancamento = this.lancamentoRepository.findById(id);

        if (!lancamento.isPresent()) return new ResponseEntity<>(new ApiResposta("erro_codigo","id_lancamento: " + id + " nao existe"),HttpStatus.NOT_FOUND);



        dto.setId_subcategoria(lancamento.get().getSubCategoria().getIdSubcategoria());
        dto.setData(lancamento.get().getData());
        dto.setId_lancamento(lancamento.get().getIdLancamento());

        this.lancamentoRepository.atualizaLancamento(
                dto.getId_lancamento(),
                dto.getValor(),
                dto.getData(),
                dto.getId_subcategoria(),
                dto.getComentario()
                );
        return new ResponseEntity<>(dto,HttpStatus.OK);

    }


}
