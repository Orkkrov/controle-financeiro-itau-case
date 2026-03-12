package com.itau.api_controle_financeiro.service;

import com.itau.api_controle_financeiro.dtos.SubCategoriaDto;
import com.itau.api_controle_financeiro.entity.CategoriaEntity;
import com.itau.api_controle_financeiro.entity.SubCategoriaEntity;
import com.itau.api_controle_financeiro.projection.SubCategoriaProjection;
import com.itau.api_controle_financeiro.repository.SubCategoriaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubCategoriaService {

    private static final Logger log =
            LoggerFactory.getLogger(SubCategoriaService.class);

    private final SubCategoriaRepository subCategoriaRepository;

    public SubCategoriaService(SubCategoriaRepository subCategoriaRepository) {
        this.subCategoriaRepository = subCategoriaRepository;
    }

    public SubCategoriaDto salvarSubCategoria(SubCategoriaDto dto) {

        if (subCategoriaRepository.existsByNome(dto.getNome())) {
            throw new RuntimeException("subcategoria ja existe");
        }

        CategoriaEntity categoria = new CategoriaEntity();
        categoria.setIdCategoria(dto.getIdCategoria());

        SubCategoriaEntity entity = new SubCategoriaEntity();
        entity.setNome(dto.getNome());
        entity.setIdCategoria(categoria);

        entity = subCategoriaRepository.save(entity);

        return new SubCategoriaDto(
                entity.getIdSubcategoria(),
                entity.getNome(),
                entity.getIdCategoria().getIdCategoria()
        );
    }

    public List<SubCategoriaDto> listarSubCategorias() {

        return subCategoriaRepository.retornaSubCategorias()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public SubCategoriaDto buscarSubCategoriaPorId(Long id) {

        SubCategoriaProjection projection = subCategoriaRepository
                .retornaSubCategoriaPeloID(id)
                .orElseThrow(() ->
                        new RuntimeException("subcategoria " + id + " nao existe"));

        return toDto(projection);
    }

    public void deletarSubCategoria(Long id) {

        log.info("deletando subcategoria {}", id);

        if (!subCategoriaRepository.existsById(id)) {
            throw new RuntimeException("subcategoria " + id + " nao existe");
        }

        subCategoriaRepository.deleteById(id);
    }

    public SubCategoriaDto atualizarSubCategoria(Long id, SubCategoriaDto dto) {

        log.info("atualizando subcategoria {}", id);

        SubCategoriaEntity entity = subCategoriaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("subcategoria " + id + " nao existe"));

        entity.setNome(dto.getNome());

        entity = subCategoriaRepository.save(entity);

        return new SubCategoriaDto(
                entity.getIdSubcategoria(),
                entity.getNome(),
                entity.getIdCategoria().getIdCategoria()
        );
    }

    private SubCategoriaDto toDto(SubCategoriaProjection p) {
        return new SubCategoriaDto(
                p.getIdSubcategoria(),
                p.getNome(),
                p.getIdCategoria()
        );
    }
}