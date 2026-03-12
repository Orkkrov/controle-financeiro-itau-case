package com.itau.api_controle_financeiro.service;

import com.itau.api_controle_financeiro.dtos.LancamentoDto;
import com.itau.api_controle_financeiro.entity.LancamentoEntity;
import com.itau.api_controle_financeiro.entity.SubCategoriaEntity;
import com.itau.api_controle_financeiro.exception.RequisicaoInvalidaException;
import com.itau.api_controle_financeiro.projection.LancamentoProjection;
import com.itau.api_controle_financeiro.repository.LancamentoRepository;
import com.itau.api_controle_financeiro.repository.SubCategoriaRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class LancamentoServiceTest {

    @Mock
    private LancamentoRepository lancamentoRepository;

    @Mock
    private SubCategoriaRepository subCategoriaRepository;

    @InjectMocks
    private LancamentoService lancamentoService;

    private SubCategoriaEntity subCategoria;
    private LancamentoEntity lancamento;

    @Before
    public void setup() {

        subCategoria = new SubCategoriaEntity();
        subCategoria.setIdSubcategoria(1L);

        lancamento = new LancamentoEntity(
                new BigDecimal("100"),
                LocalDate.of(2024,1,10),
                subCategoria,
                "teste"
        );

        lancamento.setIdLancamento(1L);
    }

    @Test
    public void deveSalvarLancamento() {

        LancamentoDto dto = new LancamentoDto(
                null,
                new BigDecimal("100"),
                LocalDate.of(2024,1,10),
                1L,
                "teste"
        );

        Mockito.when(subCategoriaRepository.findById(1L))
                .thenReturn(Optional.of(subCategoria));

        Mockito.when(lancamentoRepository.save(Mockito.any()))
                .thenReturn(lancamento);

        LancamentoDto resultado = lancamentoService.salvarLancamento(dto);

        assertEquals(Long.valueOf(1), resultado.getId_lancamento());
        assertEquals(new BigDecimal("100"), resultado.getValor());
    }

    @Test(expected = RequisicaoInvalidaException.class)
    public void naoDeveSalvarValorZero() {

        LancamentoDto dto = new LancamentoDto(
                null,
                new BigDecimal("0"),
                LocalDate.now(),
                1L,
                "teste"
        );

        lancamentoService.salvarLancamento(dto);
    }

    @Test(expected = RuntimeException.class)
    public void naoDeveSalvarSemSubcategoria() {

        LancamentoDto dto = new LancamentoDto(
                null,
                new BigDecimal("100"),
                LocalDate.now(),
                1L,
                "teste"
        );

        Mockito.when(subCategoriaRepository.findById(1L))
                .thenReturn(Optional.empty());

        lancamentoService.salvarLancamento(dto);
    }

    @Test
    public void deveListarLancamentos() {

        LancamentoProjection projection = Mockito.mock(LancamentoProjection.class);

        Mockito.when(projection.getIdLancamento()).thenReturn(1L);
        Mockito.when(projection.getValor()).thenReturn(new BigDecimal("100"));
        Mockito.when(projection.getData()).thenReturn(LocalDate.now());
        Mockito.when(projection.getIdSubcategoria()).thenReturn(1L);
        Mockito.when(projection.getComentario()).thenReturn("teste");

        Mockito.when(lancamentoRepository.retornaLancamentos())
                .thenReturn(Arrays.asList(projection));

        List<LancamentoDto> resultado = lancamentoService.listarLancamentos();

        assertEquals(1, resultado.size());
        assertEquals(Long.valueOf(1), resultado.get(0).getId_lancamento());
        assertEquals(new BigDecimal("100"), resultado.get(0).getValor());
    }

    @Test
    public void deveBuscarLancamentoPorId() {

        Mockito.when(lancamentoRepository.findById(1L))
                .thenReturn(Optional.of(lancamento));

        LancamentoDto resultado = lancamentoService.buscarLancamentoPorId(1L);

        assertEquals(Long.valueOf(1), resultado.getId_lancamento());
    }

    @Test(expected = RuntimeException.class)
    public void naoDeveBuscarLancamentoInexistente() {

        Mockito.when(lancamentoRepository.findById(1L))
                .thenReturn(Optional.empty());

        lancamentoService.buscarLancamentoPorId(1L);
    }

    @Test
    public void deveDeletarLancamento() {

        Mockito.when(lancamentoRepository.existsById(1L))
                .thenReturn(true);

        lancamentoService.deletarLancamento(1L);

        Mockito.verify(lancamentoRepository).deleteById(1L);
    }

    @Test(expected = RuntimeException.class)
    public void naoDeveDeletarLancamentoInexistente() {

        Mockito.when(lancamentoRepository.existsById(1L))
                .thenReturn(false);

        lancamentoService.deletarLancamento(1L);
    }

    @Test
    public void deveAtualizarLancamento() {

        LancamentoDto dto = new LancamentoDto(
                1L,
                new BigDecimal("200"),
                LocalDate.now(),
                1L,
                "novo comentario"
        );

        Mockito.when(lancamentoRepository.findById(1L))
                .thenReturn(Optional.of(lancamento));

        Mockito.when(lancamentoRepository.save(Mockito.any()))
                .thenReturn(lancamento);

        LancamentoDto resultado = lancamentoService.atualizarLancamento(1L, dto);

        assertEquals(new BigDecimal("200"), lancamento.getValor());
    }

    @Test(expected = RuntimeException.class)
    public void naoDeveAtualizarLancamentoInexistente() {

        LancamentoDto dto = new LancamentoDto();

        Mockito.when(lancamentoRepository.findById(1L))
                .thenReturn(Optional.empty());

        lancamentoService.atualizarLancamento(1L, dto);
    }
}