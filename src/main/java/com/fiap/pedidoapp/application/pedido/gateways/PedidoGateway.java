package com.fiap.pedidoapp.application.pedido.gateways;

import java.util.List;

import com.fiap.pedidoapp.domain.pedido.entity.Pedido;

public interface PedidoGateway {

    public List<Pedido> listarPedidos();

    public Pedido checkout(Pedido pedido);

    public Pedido buscarPorId(Integer id);

    void atualizarStatusPedido(Integer id, String novoStatus);

}
