package com.fiap.pedidoapp.application.pedido.usecases;

import com.fiap.pedidoapp.application.pedido.gateways.PedidoGateway;
import com.fiap.pedidoapp.domain.cliente.entity.Cliente;
import com.fiap.pedidoapp.domain.pedido.entity.Pedido;
import com.fiap.pedidoapp.domain.pedido.entity.StatusPedido;
import com.fiap.pedidoapp.infrastructure.pedido.controllers.TestHelper;
import com.fiap.pedidoapp.infrastructure.pedido.controllers.dto.ResumoItemDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class BuscarPedidoPorIdTest {
    @Mock
    private PedidoGateway pedidoGateway;

    @InjectMocks
    private BuscarPedidoPorId buscarPedidoPorId;

    @Test
    public void buscarPorId() {
        Pedido pedido = Pedido.builder()
                .idPedido(123)
                .statusPedido(StatusPedido.builder().idStatusPedido(123).descricao("descricao").build())
                .cliente(Cliente.builder().build())
                .valorTotal(BigDecimal.valueOf(250))
                .itens(TestHelper.getItensPedido())
                .data(LocalDateTime.now())
                .build();

        when(pedidoGateway.buscarPorId(anyInt())).thenReturn(pedido);

        buscarPedidoPorId.buscarPorId(123);
    }

}