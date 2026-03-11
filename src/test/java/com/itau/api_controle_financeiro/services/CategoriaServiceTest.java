package com.itau.api_controle_financeiro.services;

import com.itau.api_controle_financeiro.dtos.ApiResposta;
import com.itau.api_controle_financeiro.entities.CategoriaEntity;
import com.itau.api_controle_financeiro.repositories.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.MockitoAnnotations;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void deveSalvarCategoria() {

        CategoriaEntity categoria = new CategoriaEntity(1l, "t6es");

        CategoriaEntity categoriaSalva = new CategoriaEntity(1L, "t6ess");

        when(categoriaRepository.save(any())).thenReturn(categoriaSalva);

        ResponseEntity<Object> resposta =
                categoriaService.salvaCategoria(categoria);

        assertEquals(201, resposta.getStatusCodeValue());

        verify(categoriaRepository, times(1)).save(any());
    }

    @Test
    void deveRetornarCategorias() {


        when(categoriaRepository.findAll())
                .thenReturn(Arrays.asList(new CategoriaEntity(1L, "Casa")));

        ResponseEntity<Object> resposta =
                categoriaService.retornaCategorias();

        assertEquals(200, resposta.getStatusCodeValue());

        verify(categoriaRepository, times(1)).findAll();
    }

    @Test
    void deveRetornarCategoriaPorId() {

        CategoriaEntity categoria = new CategoriaEntity(1L,"Casa");

        when(categoriaRepository.findById(1L))
                .thenReturn(Optional.of(categoria));

        ResponseEntity<Object> resposta =
                categoriaService.retornaCategoriaPeloId(1L);

        assertEquals(200, resposta.getStatusCodeValue());
    }

    @Test
    void deveRetornarErroCategoriaNaoExiste() {

        when(categoriaRepository.findById(1L))
                .thenReturn(Optional.empty());

        ResponseEntity<Object> resposta =
                categoriaService.retornaCategoriaPeloId(1L);

        assertEquals(400, resposta.getStatusCodeValue());

        ApiResposta erro = (ApiResposta) resposta.getBody();

        assertEquals("erro_categoria", erro.getCodigo());
    }

    @Test
    void deveDeletarCategoria() {

        doNothing().when(categoriaRepository).deleteById(1L);

        ResponseEntity<Object> resposta =
                categoriaService.deletaCategoriaPeloId(1L);

        assertEquals(200, resposta.getStatusCodeValue());

        verify(categoriaRepository, times(1)).deleteById(1L);
    }
    @Test
    void erroAoDeletarCategoria() {

        doThrow(new RuntimeException())
                .when(categoriaRepository).deleteById(1L);

        ResponseEntity<Object> resposta =
                categoriaService.deletaCategoriaPeloId(1L);

        assertEquals(400, resposta.getStatusCodeValue());
    }

    @Test
    void deveAtualizarCategoria() {

        CategoriaEntity antiga = new CategoriaEntity(1L,"Casa");

        when(categoriaRepository.findById(1L))
                .thenReturn(Optional.of(antiga));

        CategoriaEntity nova = new CategoriaEntity(1l,"Nova");

        ResponseEntity<Object> resposta =
                categoriaService.atualizaCategoria(1L, nova);

        assertEquals(200, resposta.getStatusCodeValue());

        verify(categoriaRepository)
                .atualizaCategoria(1L,"Nova");
    }

}
