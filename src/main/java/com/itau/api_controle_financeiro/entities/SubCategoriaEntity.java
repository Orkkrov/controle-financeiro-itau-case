package com.itau.api_controle_financeiro.entities;


import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "subcategoria")
public class SubCategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subCategoria")
    private Long idSubcategoria;

    @Column(nullable = false, unique = true)
    private String nome;

    @ManyToOne
    @JoinColumn(name="id_categoria")
    private CategoriaEntity idCategoria;

    @OneToMany(mappedBy = "subCategoria")
    private List<LancamentoEntity> lancamentos;


    public SubCategoriaEntity() {
    }

    public SubCategoriaEntity(Long idSubcategoria,String nome) {
        this.idSubcategoria = idSubcategoria;
        this.nome = nome;
    }

    public SubCategoriaEntity(String nome, CategoriaEntity idCategoria) {
        this.nome = nome;
        this.idCategoria = idCategoria;
    }

    public SubCategoriaEntity(Long idSubcategoria, String nome, CategoriaEntity idCategoria, List<LancamentoEntity> lancamentos) {
        this.idSubcategoria = idSubcategoria;
        this.nome = nome;
        this.idCategoria = idCategoria;
        this.lancamentos = lancamentos;
    }

    public Long getIdSubcategoria() {
        return idSubcategoria;
    }

    public void setIdSubcategoria(Long idSubcategoria) {
        this.idSubcategoria = idSubcategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CategoriaEntity getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(CategoriaEntity idCategoria) {
        this.idCategoria = idCategoria;
    }

    public List<LancamentoEntity> getLancamentos() {
        return lancamentos;
    }

    public void setLancamentos(List<LancamentoEntity> lancamentos) {
        this.lancamentos = lancamentos;
    }


}
