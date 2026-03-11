package com.itau.api_controle_financeiro.interceptor;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        String token = request.getHeader("api-key");

        if(token == null || !token.equals("aXRhw7o=")){
            throw new RuntimeException("Token inválido");
        }

        return true;
    }
}