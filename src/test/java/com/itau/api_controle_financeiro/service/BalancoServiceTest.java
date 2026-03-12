package com.itau.api_controle_financeiro.service;
import com.itau.api_controle_financeiro.dtos.BalancoDto;
import com.itau.api_controle_financeiro.projection.BalancoProjection;
import com.itau.api_controle_financeiro.repository.LancamentoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BalancoServiceTest {

    @Mock
    private LancamentoRepository lancamentoRepository;

    @InjectMocks
    private BalancoService balancoService;

    private BalancoProjection projectionMock;

    @Before
    public void setup() {

        projectionMock = Mockito.mock(BalancoProjection.class);

        Mockito.when(projectionMock.getIdCategoria()).thenReturn(1L);
        Mockito.when(projectionMock.getNomeCategoria()).thenReturn("Alimentação");
        Mockito.when(projectionMock.getReceita()).thenReturn(new BigDecimal("1000"));
        Mockito.when(projectionMock.getDespesa()).thenReturn(new BigDecimal("400"));
    }

    @Test
    public void deveConsultarBalancoSemCategoria() {

        LocalDate inicio = LocalDate.of(2021,1,1);
        LocalDate fim = LocalDate.of(2021,1,31);

        Mockito.when(lancamentoRepository.buscarBalanco(inicio,fim))
                .thenReturn(projectionMock);

        BalancoDto resultado = balancoService.consultarBalanco(inicio,fim,null);

        assertEquals(Long.valueOf(1), resultado.getIdCategoria());
        assertEquals(new BigDecimal("1000"), resultado.getReceita());
        assertEquals(new BigDecimal("400"), resultado.getDespesa());
        assertEquals(new BigDecimal("600"), resultado.getSaldo());
    }

    @Test
    public void deveConsultarBalancoComCategoria() {

        LocalDate inicio = LocalDate.of(2021,1,1);
        LocalDate fim = LocalDate.of(2021,1,31);

        Mockito.when(lancamentoRepository.buscarBalancoComId(inicio,fim,1L))
                .thenReturn(projectionMock);

        BalancoDto resultado = balancoService.consultarBalanco(inicio,fim,1L);

        assertEquals(Long.valueOf(1), resultado.getIdCategoria());
        assertEquals(new BigDecimal("600"), resultado.getSaldo());
    }
}