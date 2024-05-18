package com.fiap.pedidoapp.application.pedido.usecases;

import com.fiap.pedidoapp.application.pagamento.clients.PagamentoClient;
import com.fiap.pedidoapp.application.pedido.gateways.PedidoGateway;
import com.fiap.pedidoapp.domain.pedido.entity.Pedido;
import com.fiap.pedidoapp.domain.pedido.entity.StatusPedido;
import com.fiap.pedidoapp.infrastructure.pagamento.clients.dto.PagamentoResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RealizarPagamentoTest {

    @InjectMocks
    private RealizarPagamento realizarPagamento;

    @Mock
    private PedidoGateway pedidoGateway;

    @Mock
    private PagamentoClient pagamentoClient;

    @Test
    public void atualizaStatusPago() {
        Pedido pedido = new Pedido();
        pedido.setIdPedido(1);
        pedido.setValorTotal(new BigDecimal(100));
        pedido.setStatusPedido(StatusPedido.builder().idStatusPedido(1).descricao("PAGO").build());

        when(pagamentoClient.realizarPagamento(pedido.getIdPedido(), pedido.getValorTotal()))
                .thenReturn(new PagamentoResponse());

        realizarPagamento.pagar(pedido);

    }
}
