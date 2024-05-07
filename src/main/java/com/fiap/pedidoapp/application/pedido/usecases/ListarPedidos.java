package com.fiap.pedidoapp.application.pedido.usecases;

import java.util.Comparator;
import java.util.List;

import com.fiap.pedidoapp.application.pedido.gateways.PedidoGateway;
import com.fiap.pedidoapp.infrastructure.pedido.controllers.dto.PedidoListaDTO;

public class ListarPedidos {

    private final PedidoGateway pedidoGateway;

    public ListarPedidos(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    public List<PedidoListaDTO> listarPedidos() {
        List<PedidoListaDTO> lista = pedidoGateway.listarPedidos().stream().map(PedidoListaDTO::new)
                .toList();

        return lista.stream()
                .filter(p -> !p.getStatusPedido().equals("Finalizado"))
                .sorted(Comparator.comparing(PedidoListaDTO::getStatusOrder)
                        .thenComparing(PedidoListaDTO::getDataHoraPedido))
                .toList();
    }

}
