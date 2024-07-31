package com.fiap.pedidoapp.application.pedido.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fiap.pedidoapp.application.pedido.gateways.PedidoGateway;
import com.fiap.pedidoapp.application.pedido.messages.PedidoMessageClient;
import com.fiap.pedidoapp.domain.pedido.entity.Pedido;
import com.fiap.pedidoapp.domain.pedido.entity.StatusPedido;
import com.fiap.pedidoapp.domain.pedido.enums.StatusPedidoEnum;

@ExtendWith(MockitoExtension.class)
class RealizarPagamentoTest {

    @InjectMocks
    private RealizarPagamento realizarPagamento;

    @Mock
    private PedidoGateway pedidoGateway;

    @Mock
    private PedidoMessageClient pedidoMessageClient;

    @Test
    void testAtualizaStatusPago() {
        Pedido pedido = new Pedido();
        pedido.setIdPedido(1);
        pedido.setValorTotal(new BigDecimal(100));
        pedido.setStatusPedido(StatusPedido.builder().idStatusPedido(1).descricao(StatusPedidoEnum.PAGO.getDescricao()).build());

        doNothing().when(pedidoMessageClient).enviarPedidoParaFilaDePagamento(anyInt(), any(BigDecimal.class));

        realizarPagamento.pagar(pedido);

        assertEquals(StatusPedidoEnum.PAGO.getDescricao(), pedido.getStatusPedido().getDescricao());

    }

    @Test
    void testAtualizaStatusPagoPagamentoAprovado() {
        Pedido pedido = new Pedido();
        pedido.setIdPedido(1);
        pedido.setValorTotal(new BigDecimal(100));
        pedido.setStatusPedido(StatusPedido.builder().idStatusPedido(1).descricao(StatusPedidoEnum.PAGO.getDescricao()).build());

        doNothing().when(pedidoMessageClient).enviarPedidoParaFilaDePagamento(anyInt(), any(BigDecimal.class));

        realizarPagamento.pagar(pedido);

        assertEquals(StatusPedidoEnum.PAGO.getDescricao(), pedido.getStatusPedido().getDescricao());

    }

    @Test
    void testAtualizaStatusPagoReturnNull() {
        Pedido pedido = new Pedido();
        pedido.setIdPedido(1);
        pedido.setValorTotal(new BigDecimal(100));
        pedido.setStatusPedido(StatusPedido.builder().idStatusPedido(1).descricao(StatusPedidoEnum.AGUARDANDO_PAGAMENTO.getDescricao()).build());

        doNothing().when(pedidoMessageClient).enviarPedidoParaFilaDePagamento(anyInt(), any(BigDecimal.class));

        realizarPagamento.pagar(pedido);

        assertEquals(StatusPedidoEnum.AGUARDANDO_PAGAMENTO.getDescricao() , pedido.getStatusPedido().getDescricao());

    }
}
