package com.fiap.pedidoapp.application.pedido.usecases;

import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fiap.pedidoapp.application.pagamento.clients.PagamentoClient;
import com.fiap.pedidoapp.application.pedido.gateways.PedidoGateway;
import com.fiap.pedidoapp.domain.pedido.entity.Pedido;
import com.fiap.pedidoapp.domain.pedido.enums.StatusPedidoEnum;
import com.fiap.pedidoapp.infrastructure.pagamento.clients.dto.PagamentoResponse;


public class RealizarPagamento {

    private final PedidoGateway pedidoGateway;
    private final PagamentoClient pagamentoClient;
   
    public RealizarPagamento(PedidoGateway pedidoGateway, PagamentoClient pagamentoClient) {
        this.pedidoGateway = pedidoGateway;
        this.pagamentoClient = pagamentoClient;
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
    public void pagar(Pedido pedido) {
        var novoStatusPedido = StatusPedidoEnum.AGUARDANDO_PAGAMENTO;
        pedidoGateway.atualizarStatusPedido(pedido.getIdPedido(), StatusPedidoEnum.AGUARDANDO_PAGAMENTO.getCodigo());
        PagamentoResponse pagamentoResponse = pagamentoClient.realizarPagamento(pedido.getIdPedido(), pedido.getValorTotal());
        if(pagamentoResponse != null && pagamentoResponse.getStatusPagamento() != null) {
            novoStatusPedido =  StatusPedidoEnum.PAGO;
        }
        pedidoGateway.atualizarStatusPedido(pedido.getIdPedido(), novoStatusPedido.getCodigo());
    }

}
