package com.itau.api_controle_financeiro.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface LancamentoProjection {
    Long getIdLancamento();
    BigDecimal getValor();
    LocalDate getData();
    Long getIdSubcategoria();
    String getComentario();

}

