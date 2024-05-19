package com.fiap.pedidoapp.application.pedido.usecases;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fiap.pedidoapp.TestHelper;
import com.fiap.pedidoapp.application.pedido.gateways.PedidoGateway;
import com.fiap.pedidoapp.domain.pedido.entity.Pedido;

@ExtendWith(SpringExtension.class)
class RealizarCheckoutTest {
    @Mock
    private RealizarPagamento realizarPagamento;

    @Mock
    private PedidoGateway pedidoGateway;

    @InjectMocks
    private RealizarCheckout realizarCheckout;

    @Test
    void checkout() {
        when(pedidoGateway.checkout(any(Pedido.class))).thenReturn(TestHelper.getPedido());
        var result = realizarCheckout.checkout(TestHelper.getNovoPedidoDTO());
    }

    @Test
    void testCheckout() {
        when(pedidoGateway.checkout(any(Pedido.class))).thenReturn(TestHelper.getPedido());
        var result = realizarCheckout.checkout(TestHelper.getNovoPedidoDTO(), "12345678912");
    }
}