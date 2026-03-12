package com.itau.api_controle_financeiro.dtos;

public class SubCategoriaDto {
    private String nome;
    private Long idCategoria;
    private Long idSubcategoria;

    public SubCategoriaDto() {
    }

    public SubCategoriaDto(String nome, Long idCategoria) {
        this.nome = nome;
        this.idCategoria = idCategoria;
    }

    public SubCategoriaDto(Long idSubcategoria, String nome, Long idCategoria) {
        this.idSubcategoria= idSubcategoria;
        this.nome= nome;
        this.idCategoria= idCategoria;
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

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public String toString() {
        return "SubCategoriaDto{" +
                "nome='" + nome + '\'' +
                ", idCategoria=" + idCategoria +
                ", idSubcategoria=" + idSubcategoria +
                '}';
    }
}
