package com.fiap.pedidoapp.infrastructure.pedido.controllers.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.fiap.pedidoapp.TestHelper;
import com.fiap.pedidoapp.domain.cliente.entity.Cliente;
import com.fiap.pedidoapp.domain.pedido.entity.Pedido;
import com.fiap.pedidoapp.domain.pedido.entity.StatusPedido;

class PedidoListaDTOTest {

    @Test
    void getStatusOrder_Recebido() {
        var pedido = Pedido.builder()
                .idPedido(123)
                .statusPedido(StatusPedido.builder().idStatusPedido(1).descricao("Recebido").build())
                .cliente(Cliente.builder().build())
                .valorTotal(BigDecimal.valueOf(250))
                .itens(TestHelper.getItensPedido())
                .data(LocalDateTime.now())
                .build();

        PedidoListaDTO pedidoListaDTO = new PedidoListaDTO(pedido);

        var result = pedidoListaDTO.getStatusOrder();

        assertEquals(4, result);
    }

    @Test
    void getStatusOrder_AguardandoPagamento() {
        var pedido = Pedido.builder()
                .idPedido(123)
                .statusPedido(StatusPedido.builder().idStatusPedido(2).descricao("Aguardando pagamento").build())
                .cliente(Cliente.builder().build())
                .valorTotal(BigDecimal.valueOf(250))
                .itens(TestHelper.getItensPedido())
                .data(LocalDateTime.now())
                .build();

        PedidoListaDTO pedidoListaDTO = new PedidoListaDTO(pedido);

        var result = pedidoListaDTO.getStatusOrder();

        assertEquals(3, result);
    }

    @Test
    void getStatusOrder_Pago() {
        var pedido = Pedido.builder()
                .idPedido(123)
                .statusPedido(StatusPedido.builder().idStatusPedido(3).descricao("Pago").build())
                .cliente(Cliente.builder().build())
                .valorTotal(BigDecimal.valueOf(250))
                .itens(TestHelper.getItensPedido())
                .data(LocalDateTime.now())
                .build();

        PedidoListaDTO pedidoListaDTO = new PedidoListaDTO(pedido);

        var result = pedidoListaDTO.getStatusOrder();

        assertEquals(2, result);
    }

    @Test
    void getStatusOrder_EmPreparacao() {
        var pedido = Pedido.builder()
                .idPedido(123)
                .statusPedido(StatusPedido.builder().idStatusPedido(4).descricao("Em preparação").build())
                .cliente(Cliente.builder().build())
                .valorTotal(BigDecimal.valueOf(250))
                .itens(TestHelper.getItensPedido())
                .data(LocalDateTime.now())
                .build();

        PedidoListaDTO pedidoListaDTO = new PedidoListaDTO(pedido);

        var result = pedidoListaDTO.getStatusOrder();

        assertEquals(1, result);
    }

    @Test
    void getStatusOrder_Pronto() {
        var pedido = Pedido.builder()
                .idPedido(123)
                .statusPedido(StatusPedido.builder().idStatusPedido(1).descricao("Pronto").build())
                .cliente(Cliente.builder().build())
                .valorTotal(BigDecimal.valueOf(250))
                .itens(TestHelper.getItensPedido())
                .data(LocalDateTime.now())
                .build();

        PedidoListaDTO pedidoListaDTO = new PedidoListaDTO(pedido);

        var result = pedidoListaDTO.getStatusOrder();

        assertEquals(0, result);
    }

    @Test
    void getStatusOrder_Finalizado() {
        var pedido = Pedido.builder()
                .idPedido(123)
                .statusPedido(StatusPedido.builder().idStatusPedido(1).descricao("Finalizado").build())
                .cliente(Cliente.builder().build())
                .valorTotal(BigDecimal.valueOf(250))
                .itens(TestHelper.getItensPedido())
                .data(LocalDateTime.now())
                .build();

        PedidoListaDTO pedidoListaDTO = new PedidoListaDTO(pedido);

        var result = pedidoListaDTO.getStatusOrder();

        assertEquals(5, result);

    }

}