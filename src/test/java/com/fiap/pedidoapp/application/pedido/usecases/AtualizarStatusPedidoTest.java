package com.fiap.pedidoapp.application.pedido.usecases;

import com.fiap.pedidoapp.application.pedido.gateways.PedidoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AtualizarStatusPedidoTest {

    @InjectMocks
    private AtualizarStatusPedido atualizarStatusPedido;

    @Mock
    private PedidoGateway pedidoGateway;

    @Test
    public void deveChamarAtualizarStatusPedido() {

        Integer idPedido = 10;
        String novoStatus = "PROCESSANDO";

        atualizarStatusPedido.atualizarStatusPedido(idPedido, novoStatus);

    }
}
