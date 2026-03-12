package com.itau.api_controle_financeiro.service;

import com.itau.api_controle_financeiro.entity.CategoriaEntity;
import com.itau.api_controle_financeiro.repository.CategoriaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    private CategoriaEntity categoria;

    @Before
    public void setup() {
        categoria = new CategoriaEntity();
        categoria.setIdCategoria(1L);
        categoria.setNome("Alimentação");
    }

    @Test
    public void deveSalvarCategoria() {

        Mockito.when(categoriaRepository.save(categoria)).thenReturn(categoria);

        CategoriaEntity resultado = categoriaService.salvarCategoria(categoria);

        assertEquals("Alimentação", resultado.getNome());
        assertEquals(Long.valueOf(1), resultado.getIdCategoria());
    }

    @Test
    public void deveListarCategorias() {

        List<CategoriaEntity> lista = Arrays.asList(categoria);

        Mockito.when(categoriaRepository.findAll()).thenReturn(lista);

        List<CategoriaEntity> resultado = categoriaService.listarCategorias();

        assertEquals(1, resultado.size());
        assertEquals("Alimentação", resultado.get(0).getNome());
    }

    @Test
    public void deveBuscarCategoriaPorId() {

        Mockito.when(categoriaRepository.findById(1L))
                .thenReturn(Optional.of(categoria));

        CategoriaEntity resultado = categoriaService.buscarCategoriaPorId(1L);

        assertEquals("Alimentação", resultado.getNome());
    }

    @Test(expected = RuntimeException.class)
    public void deveLancarErroQuandoCategoriaNaoExiste() {

        Mockito.when(categoriaRepository.findById(1L))
                .thenReturn(Optional.empty());

        categoriaService.buscarCategoriaPorId(1L);
    }

    @Test
    public void deveDeletarCategoria() {

        Mockito.when(categoriaRepository.existsById(1L))
                .thenReturn(true);

        categoriaService.deletarCategoria(1L);

        Mockito.verify(categoriaRepository).deleteById(1L);
    }

    @Test(expected = RuntimeException.class)
    public void naoDeveDeletarCategoriaInexistente() {

        Mockito.when(categoriaRepository.existsById(1L))
                .thenReturn(false);

        categoriaService.deletarCategoria(1L);
    }

    @Test
    public void deveAtualizarCategoria() {

        CategoriaEntity novaCategoria = new CategoriaEntity();
        novaCategoria.setNome("Transporte");

        Mockito.when(categoriaRepository.findById(1L))
                .thenReturn(Optional.of(categoria));

        Mockito.when(categoriaRepository.save(Mockito.any(CategoriaEntity.class)))
                .thenReturn(categoria);

        CategoriaEntity resultado =
                categoriaService.atualizarCategoria(1L, novaCategoria);

        assertEquals("Transporte", categoria.getNome());
    }

    @Test(expected = RuntimeException.class)
    public void naoDeveAtualizarCategoriaInexistente() {

        CategoriaEntity novaCategoria = new CategoriaEntity();
        novaCategoria.setNome("Transporte");

        Mockito.when(categoriaRepository.findById(1L))
                .thenReturn(Optional.empty());

        categoriaService.atualizarCategoria(1L, novaCategoria);
    }
}