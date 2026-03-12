package com.itau.api_controle_financeiro.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
public class LancamentoDto {

    private Long id_lancamento;
        private BigDecimal valor;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate data;

    private Long id_subcategoria;
    private String comentario;

    public LancamentoDto() {
    }

    public LancamentoDto(Long id_lancamento, BigDecimal valor, LocalDate data, Long id_subcategoria, String comentario) {
        this.id_lancamento = id_lancamento;
        this.valor = valor;
        this.data = data;
        this.id_subcategoria = id_subcategoria;
        this.comentario = comentario;
    }

    public LocalDate getData() {
        return data != null ? data : LocalDate.now();
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Long getId_lancamento() {
        return id_lancamento;
    }

    public void setId_lancamento(Long id_lancamento) {
        this.id_lancamento = id_lancamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Long getId_subcategoria() {
        return id_subcategoria;
    }

    public void setId_subcategoria(Long id_subcategoria) {
        this.id_subcategoria = id_subcategoria;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "LancamentoDto{" +
                "id_lancamento=" + id_lancamento +
                ", valor=" + valor +
                ", data=" + data +
                ", id_subcategoria=" + id_subcategoria +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
