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

}
