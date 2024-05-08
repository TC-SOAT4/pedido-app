package com.fiap.pedidoapp.application.pagamento.clients;

import java.math.BigDecimal;

public interface PagamentoClient {

    public void realizarPagamento(Integer idPedido, BigDecimal valor);

}
