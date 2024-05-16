package com.fiap.pedidoapp.application.pagamento.clients;

import java.math.BigDecimal;

import com.fiap.pedidoapp.infrastructure.pagamento.clients.dto.PagamentoResponse;

public interface PagamentoClient {

    public PagamentoResponse realizarPagamento(Integer idPedido, BigDecimal valor);

}
