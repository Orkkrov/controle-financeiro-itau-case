package com.itau.api_controle_financeiro.interceptor;


import com.itau.api_controle_financeiro.globalexception.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    private static final Logger log =
            LoggerFactory.getLogger(TokenInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String path = request.getRequestURI();

        if (path.startsWith("/v1/swagger-ui") ||
                path.startsWith("/v3/api-docs")) {

            return true;
        }

        String token = request.getHeader("api-key");

        if (token == null || !token.equals("aXRhw7o=")) {
            String erro = (token == null ) ? "requisicao sem header" : "requisicao com chave incorreta";

            log.error(erro);

            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");

            response.getWriter().write(
                    "{ \"codigo\": \"erro_autorizacao\", \"mensagem\": \""+erro+"\" }"
            );

            return false;
        }

        return true;
    }
}