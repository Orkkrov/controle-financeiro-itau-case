package com.itau.api_controle_financeiro.globalexception;

import com.itau.api_controle_financeiro.dtos.ApiResposta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;


@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResposta> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        String caminho = ex.getRequestURL();
        return new ResponseEntity<>(new ApiResposta("erro_caminho", "path " +caminho+ " nao existe") , HttpStatus.NOT_FOUND);
    }




    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<Object> handleMissingRequestHeaderException(NoHandlerFoundException ex) {
        return new ResponseEntity<>(new ApiResposta("erro",ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}
