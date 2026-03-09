package com.itau.api_controle_financeiro.entities;

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
    @JoinColumn(name="id_subCategoria")
    private SubCategoriaEntity subCategoria;

    private String comentario;

    public LancamentoEntity() {
    }

    public LancamentoEntity(Long idLancamento, BigDecimal valor, LocalDate data, SubCategoriaEntity subCategoria, String comentario) {
        this.idLancamento = idLancamento;
        this.valor = valor;
        this.data = data;
        this.subCategoria = subCategoria;
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
        return subCategoria;
    }

    public void setSubCategoria(SubCategoriaEntity subCategoria) {
        this.subCategoria = subCategoria;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
