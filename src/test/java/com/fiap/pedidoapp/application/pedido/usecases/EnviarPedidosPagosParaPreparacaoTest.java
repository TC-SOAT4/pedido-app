package com.fiap.pedidoapp.application.pedido.usecases;

import com.fiap.pedidoapp.application.pedido.gateways.PedidoGateway;
import com.fiap.pedidoapp.application.pedido.messages.PedidoMessageClient;
import com.fiap.pedidoapp.domain.pedido.entity.Item;
import com.fiap.pedidoapp.domain.pedido.entity.Pedido;
import com.fiap.pedidoapp.domain.produto.entity.Categoria;
import com.fiap.pedidoapp.domain.produto.entity.Produto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EnviarPedidosPagosParaPreparacaoTest {

    @InjectMocks
    private EnviarPedidosPagosParaPreparacao enviarPedidos;

    @Mock
    private PedidoGateway pedidoGateway;

    @Test
    public void enviarPedidoWithListIsEmpty() {
        when(pedidoGateway.listarPedidosPagos()).thenReturn(new ArrayList<>());
        enviarPedidos.enviar();
    }

    @Test
    public void enviarPedidoWithListNotEmpty() {
        Pedido pedidoOne = Pedido.builder()
                .idPedido(10)
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

        when(pedidoGateway.listarPedidosPagos()).thenReturn(List.of(pedidoOne));
        enviarPedidos.enviar();
    }
}
