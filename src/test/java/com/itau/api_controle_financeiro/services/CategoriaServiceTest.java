package com.itau.api_controle_financeiro.services;

import org.junit.Assert;
import org.junit.jupiter.api.Test;


class CategoriaServiceTest {
    @Test
    void deveRetornarUnauthirized401() {
        String chaveApi = "chave-teste";
        long statusCode = 200l;

        if (!chaveApi.equals("aXRhw7o=")) statusCode = 401l;


        Assert.assertEquals(statusCode, 401l);

    }


    @Test
    void deveRetornarOk200() {
        String chaveApi = "aXRhw7o=";
        long statusCode = 200l;

        if (!chaveApi.equals("aXRhw7o=")) statusCode = 401l;


        Assert.assertEquals(statusCode, 200l);

    }


}