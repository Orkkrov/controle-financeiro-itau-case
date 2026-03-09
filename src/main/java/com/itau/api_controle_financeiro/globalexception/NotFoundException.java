
package com.itau.api_controle_financeiro.globalexception;

import com.itau.api_controle_financeiro.dtos.ApiResposta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
class NotFoundException {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        return new ResponseEntity<>(new ApiResposta("erro",ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}