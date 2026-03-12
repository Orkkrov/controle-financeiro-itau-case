package com.itau.api_controle_financeiro.globalexception;

import com.itau.api_controle_financeiro.dtos.ApiResposta;
import com.itau.api_controle_financeiro.exception.NaoAutorizadoException;
import com.itau.api_controle_financeiro.exception.RecursoNaoEncontradoException;
import com.itau.api_controle_financeiro.exception.RequisicaoInvalidaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


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


    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ApiResposta> handleNotFound(RecursoNaoEncontradoException ex) {
        ApiResposta erro = new ApiResposta("erro_nao_encontrado", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(RequisicaoInvalidaException.class)
    public ResponseEntity<ApiResposta> handleBadRequest(RequisicaoInvalidaException ex) {
        ApiResposta erro = new ApiResposta("erro_validacao", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(NaoAutorizadoException.class)
    public ResponseEntity<ApiResposta> handleUnauthorized(NaoAutorizadoException ex) {
        ApiResposta erro = new ApiResposta("erro_nao_autorizado", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(erro);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResposta> handleException(Exception ex) {
        ApiResposta erro = new ApiResposta("erro_interno", "Ocorreu um erro inesperado");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }


}
