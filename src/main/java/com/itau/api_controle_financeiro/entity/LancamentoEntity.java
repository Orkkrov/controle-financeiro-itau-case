package com.itau.api_controle_financeiro.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "lancamento")
public class LancamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lancamento")
    private Long idLancamento;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name="id_subcategoria")
    private SubCategoriaEntity subcategoria;

    private String comentario;

    public LancamentoEntity() {
    }

    public LancamentoEntity(BigDecimal valor, LocalDate data, SubCategoriaEntity subcategoria, String comentario) {
        this.valor = valor;
        this.data = data;
        this.subcategoria = subcategoria;
        this.comentario = comentario;
    }

    public Long getIdLancamento() {
        return idLancamento;
    }

    public void setIdLancamento(Long idLancamento) {
        this.idLancamento = idLancamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public SubCategoriaEntity getSubCategoria() {
        return subcategoria;
    }

    public void setSubCategoria(SubCategoriaEntity subCategoria) {
        this.subcategoria = subCategoria;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
