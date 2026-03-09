package com.itau.api_controle_financeiro.entities;


import javax.persistence.*;

@Entity
@Table(name = "categoria")
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_categoria;
    @Column(nullable = false, unique = true)
    private String nome;

    public CategoriaEntity() {
    }

    public CategoriaEntity(Long id_categoria, String nome) {
        this.nome = nome;
        this.id_categoria = id_categoria;
    }

    public Long getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(Long id_categoria) {
        this.id_categoria = id_categoria;
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
                "id_categoria=" + id_categoria +
                ", nome='" + nome + '\'' +
                '}';
    }
}
