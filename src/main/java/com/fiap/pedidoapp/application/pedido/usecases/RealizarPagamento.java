package com.fiap.pedidoapp.application.pedido.usecases;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fiap.pedidoapp.application.pedido.gateways.PedidoGateway;
import com.fiap.pedidoapp.application.pedido.messages.PedidoMessageClient;
import com.fiap.pedidoapp.domain.pedido.entity.Pedido;
import com.fiap.pedidoapp.domain.pedido.enums.StatusPedidoEnum;

@Component
public class RealizarPagamento {

    private final PedidoGateway pedidoGateway;
    private final PedidoMessageClient pedidoMessageClient;

    public RealizarPagamento(PedidoGateway pedidoGateway, PedidoMessageClient pedidoMessageClient) {
        this.pedidoGateway = pedidoGateway;
        this.pedidoMessageClient = pedidoMessageClient;
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
    public void pagar(Pedido pedido) {
        pedidoGateway.atualizarStatusPedido(pedido.getIdPedido(), StatusPedidoEnum.AGUARDANDO_PAGAMENTO.getCodigo());        
        pedidoMessageClient.enviarPedidoParaFilaDePagamento(pedido.getIdPedido(), pedido.getValorTotal());
    }

}
