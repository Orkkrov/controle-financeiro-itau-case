package com.itau.api_controle_financeiro.globalexception;

import com.itau.api_controle_financeiro.dtos.ApiResposta;
import com.itau.api_controle_financeiro.services.CategoriaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;


@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResposta> handleInvalidJson(HttpMessageNotReadableException ex) {

        ApiResposta response = new ApiResposta(
                "erro_json",
                "O corpo da requisição está inválido"
        );


        log.error("requisicao com corpo invalido: " + ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}
