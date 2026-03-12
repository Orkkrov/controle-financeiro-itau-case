package com.itau.api_controle_financeiro.service;

import com.itau.api_controle_financeiro.entity.CategoriaEntity;
import com.itau.api_controle_financeiro.exception.RequisicaoInvalidaException;
import com.itau.api_controle_financeiro.repository.CategoriaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private static final Logger log =
            LoggerFactory.getLogger(CategoriaService.class);

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public CategoriaEntity salvarCategoria(CategoriaEntity categoria) {

        if (categoria.getNome() == null ||
                categoria.getNome().isEmpty()) {
            throw new RequisicaoInvalidaException("O campo 'nome' é obrigatório");
        }

        if(categoriaRepository.findByNome(categoria.getNome()).isPresent()) {
            throw new RequisicaoInvalidaException("Categoria com esse nome já existe");
        }


        log.info("Criando categoria com nome {}", categoria.getNome());

        return categoriaRepository.save(categoria);
    }

    public List<CategoriaEntity> listarCategorias() {

        log.info("Buscando todas as categorias");

        return categoriaRepository.findAll();
    }

    public CategoriaEntity buscarCategoriaPorId(Long id) {

        log.info("Buscando categoria com id {}", id);

        return categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new RequisicaoInvalidaException("Categoria com id " + id + " não encontrada"));
    }

    public void deletarCategoria(Long id) {

        log.info("Deletando categoria com id {}", id);

        if (!categoriaRepository.existsById(id)) {
            throw new RequisicaoInvalidaException("Categoria com id " + id + " não encontrada");
        }

        categoriaRepository.deleteById(id);
    }

    public CategoriaEntity atualizarCategoria(Long id, CategoriaEntity categoriaAtualizada) {

        log.info("Atualizando categoria {} com nome {}", id, categoriaAtualizada.getNome());

        CategoriaEntity categoria = categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new RequisicaoInvalidaException("Categoria com id " + id + " não encontrada"));

        categoria.setNome(categoriaAtualizada.getNome());

        return categoriaRepository.save(categoria);
    }
}