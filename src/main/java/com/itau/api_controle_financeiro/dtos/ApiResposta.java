package com.itau.api_controle_financeiro.dtos;

public class ApiResposta {

    private String codigo;
    private String mensagem;

    public ApiResposta(String codigo, String mensagem) {
        this.codigo = codigo;
        this.mensagem = mensagem;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getMensagem() {
        return mensagem;
    }
}