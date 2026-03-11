package com.itau.api_controle_financeiro.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BalancoController {
    @GetMapping("/balanco")
    public Map<String , String> retornaBalanco(){
        Map<String , String> conta = new HashMap<>();
        conta.put("data_consulta", String.valueOf(LocalDateTime.now()));
        conta.put("balanco_total", String.valueOf(20000));
        return conta;
    }
}
