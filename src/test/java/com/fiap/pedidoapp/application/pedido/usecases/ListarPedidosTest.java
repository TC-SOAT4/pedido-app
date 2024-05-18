package com.fiap.pedidoapp.application.pedido.usecases;

import com.fiap.pedidoapp.application.pedido.gateways.PedidoGateway;
import com.fiap.pedidoapp.domain.pedido.entity.Item;
import com.fiap.pedidoapp.domain.pedido.entity.Pedido;
import com.fiap.pedidoapp.domain.pedido.entity.StatusPedido;
import com.fiap.pedidoapp.domain.produto.entity.Categoria;
import com.fiap.pedidoapp.domain.produto.entity.Produto;
import com.fiap.pedidoapp.infrastructure.pedido.controllers.dto.PedidoListaDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListarPedidosTest {

    @InjectMocks
    private ListarPedidos listarPedidos;

    @Mock
    private PedidoGateway pedidoGateway;

    @Test
    public void listarPedidos() {
        Pedido pedidoOne = Pedido.builder()
                .idPedido(10)
                .data(LocalDateTime.now())
                .statusPedido(StatusPedido.builder().idStatusPedido(10).descricao("Finalizado").build())
                .itens(List.of(
                        Item.builder()
                                .idItem("10")
                                .produto(
                                        Produto.builder()
                                                .idProduto(10)
                                                .ativo(Boolean.TRUE)
                                                .categoria(Categoria.builder().idCategoria(10).build())
                                                .build()
                                )
                                .quantidade(10)
                                .build()
                ))
                .build();

        Pedido pedidoTwo = Pedido.builder()
                .idPedido(10)
                .statusPedido(StatusPedido.builder().idStatusPedido(10).descricao("Aguardando").build())
                .data(LocalDateTime.now())
                .itens(List.of(
                        Item.builder()
                                .idItem("10")
                                .produto(
                                        Produto.builder()
                                                .idProduto(10)
                                                .ativo(Boolean.TRUE)
                                                .categoria(Categoria.builder().idCategoria(10).build())
                                                .build()
                                )
                                .quantidade(10)
                                .build()
                ))
                .build();

        when(pedidoGateway.listarPedidos()).thenReturn(List.of(pedidoOne, pedidoTwo));


        List<PedidoListaDTO> resultado = listarPedidos.listarPedidos();

        assertEquals(1, resultado.size());
        assertEquals("Aguardando", resultado.get(0).getStatusPedido());
        verify(pedidoGateway).listarPedidos();
    }
}
