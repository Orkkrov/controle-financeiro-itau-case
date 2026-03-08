package com.itau.api_controle_financeiro.dtos;

public class CategoriaDto {
    private Long id_categoria;
    private String nome;

    public CategoriaDto(Long id_categoria, String nome) {
        this.id_categoria = id_categoria;
        this.nome = nome;
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
}
