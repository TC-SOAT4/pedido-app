package com.fiap.pedidoapp.application.pedido.usecases;

import com.fiap.pedidoapp.application.pedido.gateways.PedidoGateway;
import com.fiap.pedidoapp.domain.pedido.entity.Pedido;
import com.fiap.pedidoapp.infrastructure.pedido.controllers.dto.ResumoPedidoDTO;

public class BuscarPedidoPorId {

    private final PedidoGateway pedidoGateway;

    public BuscarPedidoPorId(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    public ResumoPedidoDTO buscarPorId(Integer id) {
        Pedido pedido =  this.pedidoGateway.buscarPorId(id);
        return new ResumoPedidoDTO(pedido);
    }
}
