package com.fiap.pedidoapp.infrastructure.pedido.controllers;

import com.fiap.pedidoapp.application.pedido.usecases.AtualizarStatusPedido;
import com.fiap.pedidoapp.application.pedido.usecases.BuscarPedidoPorId;
import com.fiap.pedidoapp.application.pedido.usecases.ListarPedidos;
import com.fiap.pedidoapp.application.pedido.usecases.RealizarCheckout;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class PedidoControllerTest {

    @Mock
    private RealizarCheckout realizarCheckout;

    @Mock
    private ListarPedidos listarPedidos;

    @Mock
    private BuscarPedidoPorId buscarPedidoPorId;

    @Mock
    private AtualizarStatusPedido atualizarStatusPedido;

    @InjectMocks
    private PedidoController pedidoController;

    @Test
    public void testCriarNovoPedido() {
        var result = pedidoController.criarNovoPedido(TestHelper.getNovoPedidoDTO());
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testListarPedidos() {
        var result = pedidoController.listarPedidos();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testBuscar() {
        var result = pedidoController.buscar(123);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testAtualizarStatusPedido() {
        var result = pedidoController.atualizarStatusPedido(123, "foo");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}