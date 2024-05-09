package com.fiap.pedidoapp.application.pedido.usecases;

import com.fiap.pedidoapp.application.pedido.gateways.PedidoGateway;

public class AtualizarStatusPedido {

    private final PedidoGateway pedidoGateway;

    public AtualizarStatusPedido(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    public void atualizarStatusPedido(Integer id, String novoStatus) {
        pedidoGateway.atualizarStatusPedido(id, novoStatus);
    }
}
