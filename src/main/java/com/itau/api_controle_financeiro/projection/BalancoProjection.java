package com.itau.api_controle_financeiro.projection;

import java.math.BigDecimal;

public interface BalancoProjection {
    Long getIdCategoria();

    String getNomeCategoria();

    BigDecimal getReceita();

    BigDecimal getDespesa();


}
