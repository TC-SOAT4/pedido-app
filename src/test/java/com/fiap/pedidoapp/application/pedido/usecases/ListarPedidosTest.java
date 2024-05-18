package com.fiap.pedidoapp.application.pedido.usecases;

import com.fiap.pedidoapp.application.pedido.gateways.PedidoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class ListarPedidosTest {
    @Mock
    private PedidoGateway pedidoGateway;

    @InjectMocks
    private ListarPedidos listarPedidos;

    @Test
    void listarPedidos() {
        var result = listarPedidos.listarPedidos();
    }
}