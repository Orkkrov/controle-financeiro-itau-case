package com.itau.api_controle_financeiro.service;

import com.itau.api_controle_financeiro.dtos.LancamentoDto;
import com.itau.api_controle_financeiro.entity.LancamentoEntity;
import com.itau.api_controle_financeiro.entity.SubCategoriaEntity;
import com.itau.api_controle_financeiro.repository.LancamentoRepository;
import com.itau.api_controle_financeiro.repository.SubCategoriaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LancamentoService {

    private static final Logger log =
            LoggerFactory.getLogger(LancamentoService.class);

    private final LancamentoRepository lancamentoRepository;
    private final SubCategoriaRepository subCategoriaRepository;

    public LancamentoService(
            LancamentoRepository lancamentoRepository,
            SubCategoriaRepository subCategoriaRepository) {

        this.lancamentoRepository = lancamentoRepository;
        this.subCategoriaRepository = subCategoriaRepository;
    }

    public LancamentoDto salvarLancamento(LancamentoDto dto) {

        if (dto.getValor().doubleValue() == 0.0) {
            throw new IllegalArgumentException("valor deve ser diferente de zero");
        }

        SubCategoriaEntity subCategoria = subCategoriaRepository
                .findById(dto.getId_subcategoria())
                .orElseThrow(() ->
                        new RuntimeException("subcategoria " + dto.getId_subcategoria() + " nao existe"));

        LancamentoEntity entity = new LancamentoEntity(
                dto.getValor(),
                dto.getData(),
                subCategoria,
                dto.getComentario()
        );

        entity = lancamentoRepository.save(entity);

        return new LancamentoDto(
                entity.getIdLancamento(),
                entity.getValor(),
                entity.getData(),
                subCategoria.getIdSubcategoria(),
                entity.getComentario()
        );
    }

    public List<LancamentoDto> listarLancamentos() {

        return lancamentoRepository.retornaLancamentos()
                .stream()
                .map(l -> new LancamentoDto(
                        l.getIdLancamento(),
                        l.getValor(),
                        l.getData(),
                        l.getIdSubcategoria(),
                        l.getComentario()))
                .collect(Collectors.toList());
    }

    public LancamentoDto buscarLancamentoPorId(Long id) {

        LancamentoEntity entity = lancamentoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("lancamento " + id + " nao existe"));

        return new LancamentoDto(
                entity.getIdLancamento(),
                entity.getValor(),
                entity.getData(),
                entity.getSubCategoria().getIdSubcategoria(),
                entity.getComentario()
        );
    }

    public void deletarLancamento(Long id) {

        log.info("vai deletar o lancamento {}", id);

        if (!lancamentoRepository.existsById(id)) {
            throw new RuntimeException("lancamento " + id + " nao existe");
        }

        lancamentoRepository.deleteById(id);
    }

    public LancamentoDto atualizarLancamento(Long id, LancamentoDto dto) {

        log.info("vai atualizar o lancamento {}", id);

        LancamentoEntity entity = lancamentoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("lancamento " + id + " nao existe"));

        entity.setValor(dto.getValor());
        entity.setComentario(dto.getComentario());

        entity = lancamentoRepository.save(entity);

        return new LancamentoDto(
                entity.getIdLancamento(),
                entity.getValor(),
                entity.getData(),
                entity.getSubCategoria().getIdSubcategoria(),
                entity.getComentario()
        );
    }
}