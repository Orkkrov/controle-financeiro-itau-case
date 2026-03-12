package com.itau.api_controle_financeiro.service;

import com.itau.api_controle_financeiro.dtos.SubCategoriaDto;
import com.itau.api_controle_financeiro.entity.CategoriaEntity;
import com.itau.api_controle_financeiro.entity.SubCategoriaEntity;
import com.itau.api_controle_financeiro.projection.SubCategoriaProjection;
import com.itau.api_controle_financeiro.repository.SubCategoriaRepository;

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
public class SubCategoriaServiceTest {

    @Mock
    private SubCategoriaRepository subCategoriaRepository;

    @InjectMocks
    private SubCategoriaService subCategoriaService;

    private SubCategoriaEntity entity;

    @Before
    public void setup() {

        CategoriaEntity categoria = new CategoriaEntity();
        categoria.setIdCategoria(1L);

        entity = new SubCategoriaEntity();
        entity.setIdSubcategoria(1L);
        entity.setNome("Restaurante");
        entity.setIdCategoria(categoria);
    }

    @Test
    public void deveSalvarSubCategoria() {

        SubCategoriaDto dto = new SubCategoriaDto(null, "Restaurante", 1L);

        Mockito.when(subCategoriaRepository.existsByNome("Restaurante"))
                .thenReturn(false);

        Mockito.when(subCategoriaRepository.save(Mockito.any()))
                .thenReturn(entity);

        SubCategoriaDto resultado = subCategoriaService.salvarSubCategoria(dto);

        assertEquals("Restaurante", resultado.getNome());
        assertEquals(Long.valueOf(1), resultado.getIdCategoria());
    }

    @Test(expected = RuntimeException.class)
    public void naoDeveSalvarSubCategoriaDuplicada() {

        SubCategoriaDto dto = new SubCategoriaDto(null, "Restaurante", 1L);

        Mockito.when(subCategoriaRepository.existsByNome("Restaurante"))
                .thenReturn(true);

        subCategoriaService.salvarSubCategoria(dto);
    }

    @Test
    public void deveListarSubCategorias() {

        SubCategoriaProjection projection = Mockito.mock(SubCategoriaProjection.class);

        Mockito.when(projection.getIdSubcategoria()).thenReturn(1L);
        Mockito.when(projection.getNome()).thenReturn("Restaurante");
        Mockito.when(projection.getIdCategoria()).thenReturn(1L);

        Mockito.when(subCategoriaRepository.retornaSubCategorias())
                .thenReturn(Arrays.asList(projection));

        List<SubCategoriaDto> resultado = subCategoriaService.listarSubCategorias();

        assertEquals(1, resultado.size());
        assertEquals("Restaurante", resultado.get(0).getNome());
    }

    @Test
    public void deveBuscarSubCategoriaPorId() {

        SubCategoriaProjection projection = Mockito.mock(SubCategoriaProjection.class);

        Mockito.when(projection.getIdSubcategoria()).thenReturn(1L);
        Mockito.when(projection.getNome()).thenReturn("Restaurante");
        Mockito.when(projection.getIdCategoria()).thenReturn(1L);

        Mockito.when(subCategoriaRepository.retornaSubCategoriaPeloID(1L))
                .thenReturn(Optional.of(projection));

        SubCategoriaDto resultado = subCategoriaService.buscarSubCategoriaPorId(1L);

        assertEquals("Restaurante", resultado.getNome());
    }

    @Test(expected = RuntimeException.class)
    public void naoDeveBuscarSubCategoriaInexistente() {

        Mockito.when(subCategoriaRepository.retornaSubCategoriaPeloID(1L))
                .thenReturn(Optional.empty());

        subCategoriaService.buscarSubCategoriaPorId(1L);
    }

    @Test
    public void deveDeletarSubCategoria() {

        Mockito.when(subCategoriaRepository.existsById(1L))
                .thenReturn(true);

        subCategoriaService.deletarSubCategoria(1L);

        Mockito.verify(subCategoriaRepository).deleteById(1L);
    }

    @Test(expected = RuntimeException.class)
    public void naoDeveDeletarSubCategoriaInexistente() {

        Mockito.when(subCategoriaRepository.existsById(1L))
                .thenReturn(false);

        subCategoriaService.deletarSubCategoria(1L);
    }

    @Test
    public void deveAtualizarSubCategoria() {

        SubCategoriaDto dto = new SubCategoriaDto(null, "Supermercado", 1L);

        Mockito.when(subCategoriaRepository.findById(1L))
                .thenReturn(Optional.of(entity));

        Mockito.when(subCategoriaRepository.save(Mockito.any()))
                .thenReturn(entity);

        SubCategoriaDto resultado = subCategoriaService.atualizarSubCategoria(1L, dto);

        assertEquals("Supermercado", entity.getNome());
    }

    @Test(expected = RuntimeException.class)
    public void naoDeveAtualizarSubCategoriaInexistente() {

        SubCategoriaDto dto = new SubCategoriaDto(null, "Teste", 1L);

        Mockito.when(subCategoriaRepository.findById(1L))
                .thenReturn(Optional.empty());

        subCategoriaService.atualizarSubCategoria(1L, dto);
    }
}