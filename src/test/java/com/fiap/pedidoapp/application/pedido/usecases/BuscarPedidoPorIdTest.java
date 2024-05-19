package com.fiap.pedidoapp.application.pedido.usecases;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fiap.pedidoapp.TestHelper;
import com.fiap.pedidoapp.application.pedido.gateways.PedidoGateway;

@ExtendWith(SpringExtension.class)
class BuscarPedidoPorIdTest {
    @Mock
    private PedidoGateway pedidoGateway;

    @InjectMocks
    private BuscarPedidoPorId buscarPedidoPorId;

    @Test
    public void buscarPorId() {
        when(pedidoGateway.buscarPorId(anyInt())).thenReturn(TestHelper.getPedido());
        buscarPedidoPorId.buscarPorId(123);
    }

}