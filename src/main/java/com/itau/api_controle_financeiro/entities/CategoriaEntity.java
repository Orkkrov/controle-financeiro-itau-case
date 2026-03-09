package com.itau.api_controle_financeiro.entities;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categoria")
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long idCategoria;
    @Column(nullable = false, unique = true)
    private String nome;
    @OneToMany(mappedBy = "idCategoria", cascade = CascadeType.ALL)
    private List<SubCategoriaEntity> subCategorias;

    public CategoriaEntity() {
    }

    public CategoriaEntity(Long idCategoria, String nome) {
        this.nome = nome;
        this.idCategoria = idCategoria;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



    @Override
    public String toString() {
        return "CategoriaEntity{" +
                "idCategoria=" + idCategoria +
                ", nome='" + nome + '\'' +
                ", subCategorias=" + subCategorias +
                '}';
    }
}
