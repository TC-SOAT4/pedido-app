package com.fiap.pedidoapp.application.pedido.usecases;

import com.fiap.pedidoapp.application.pagamento.clients.PagamentoClient;
import com.fiap.pedidoapp.application.pedido.gateways.PedidoGateway;
import com.fiap.pedidoapp.domain.pedido.entity.Pedido;
import com.fiap.pedidoapp.domain.pedido.entity.StatusPedido;
import com.fiap.pedidoapp.domain.pedido.enums.StatusPedidoEnum;
import com.fiap.pedidoapp.infrastructure.pagamento.clients.dto.PagamentoResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RealizarPagamentoTest {

    @InjectMocks
    private RealizarPagamento realizarPagamento;

    @Mock
    private PedidoGateway pedidoGateway;

    @Mock
    private PagamentoClient pagamentoClient;

    @Test
    void testAtualizaStatusPago() {
        Pedido pedido = new Pedido();
        pedido.setIdPedido(1);
        pedido.setValorTotal(new BigDecimal(100));
        pedido.setStatusPedido(StatusPedido.builder().idStatusPedido(1).descricao(StatusPedidoEnum.PAGO.getDescricao()).build());

        when(pagamentoClient.realizarPagamento(pedido.getIdPedido(), pedido.getValorTotal()))
                .thenReturn(new PagamentoResponse());

        realizarPagamento.pagar(pedido);

        assertEquals(StatusPedidoEnum.PAGO.getDescricao(), pedido.getStatusPedido().getDescricao());

    }

    @Test
    void testAtualizaStatusPagoPagamentoAprovado() {
        Pedido pedido = new Pedido();
        pedido.setIdPedido(1);
        pedido.setValorTotal(new BigDecimal(100));
        pedido.setStatusPedido(StatusPedido.builder().idStatusPedido(1).descricao(StatusPedidoEnum.PAGO.getDescricao()).build());

        var pagamentoResponse = new PagamentoResponse();
        pagamentoResponse.setStatusPagamento("Aprovado");

        when(pagamentoClient.realizarPagamento(pedido.getIdPedido(), pedido.getValorTotal()))
                .thenReturn(pagamentoResponse);

        realizarPagamento.pagar(pedido);

        assertEquals(StatusPedidoEnum.PAGO.getDescricao(), pedido.getStatusPedido().getDescricao());

    }

    @Test
    void testAtualizaStatusPagoReturnNull() {
        Pedido pedido = new Pedido();
        pedido.setIdPedido(1);
        pedido.setValorTotal(new BigDecimal(100));
        pedido.setStatusPedido(StatusPedido.builder().idStatusPedido(1).descricao(StatusPedidoEnum.AGUARDANDO_PAGAMENTO.getDescricao()).build());

        when(pagamentoClient.realizarPagamento(pedido.getIdPedido(), pedido.getValorTotal()))
                .thenReturn(null);

        realizarPagamento.pagar(pedido);

        assertEquals(StatusPedidoEnum.AGUARDANDO_PAGAMENTO.getDescricao() , pedido.getStatusPedido().getDescricao());

    }
}
