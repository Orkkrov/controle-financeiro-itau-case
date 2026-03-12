package com.itau.api_controle_financeiro.dtos;

import java.math.BigDecimal;

public class BalancoDto {
    private CategoriaDto categoria;
    private Long idCategoria;
    private BigDecimal receita;
    private BigDecimal despesa;
    private BigDecimal saldo;


    public BalancoDto(CategoriaDto categoria, Long idCategoria, BigDecimal receita, BigDecimal despesa, BigDecimal saldo) {
        this.categoria = categoria;
        this.idCategoria = idCategoria;
        this.receita = receita;
        this.despesa = despesa;
        this.saldo = saldo;
    }

    public CategoriaDto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDto categoria) {
        this.categoria = categoria;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public BigDecimal getReceita() {
        return receita;
    }

    public void setReceita(BigDecimal receita) {
        this.receita = receita;
    }

    public BigDecimal getDespesa() {
        return despesa;
    }

    public void setDespesa(BigDecimal despesa) {
        this.despesa = despesa;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }


}

