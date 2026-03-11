package com.itau.api_controle_financeiro.exception;

public class CategoriaNaoExisteException extends RuntimeException {
    public CategoriaNaoExisteException(String message) {
        super(message);
    }
}
