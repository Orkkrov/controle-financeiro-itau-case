package com.itau.api_controle_financeiro.exceptions;

public class CategoriaInexistenteException extends RuntimeException {
    public CategoriaInexistenteException(String message) {
        super(message);
    }
}
