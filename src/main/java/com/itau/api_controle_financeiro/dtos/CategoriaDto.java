package com.itau.api_controle_financeiro.dtos;

public class CategoriaDto {
    private Long idCategoria;
    private String nome;

    public CategoriaDto(Long idCategoria, String nome) {
        this.idCategoria = idCategoria;
        this.nome = nome;
    }

    public CategoriaDto(String nome) {
        this.nome = nome;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setId_categoria(Long id_categoria) {
        this.idCategoria = idCategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }
}
