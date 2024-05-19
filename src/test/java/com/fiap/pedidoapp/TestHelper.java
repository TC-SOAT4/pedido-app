package com.fiap.pedidoapp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.fiap.pedidoapp.domain.cliente.entity.Cliente;
import com.fiap.pedidoapp.domain.pedido.entity.Item;
import com.fiap.pedidoapp.domain.pedido.entity.Pedido;
import com.fiap.pedidoapp.domain.pedido.entity.StatusPedido;
import com.fiap.pedidoapp.domain.produto.entity.Categoria;
import com.fiap.pedidoapp.domain.produto.entity.Produto;
import com.fiap.pedidoapp.infrastructure.cliente.persistence.entity.ClienteEntity;
import com.fiap.pedidoapp.infrastructure.pedido.controllers.dto.ItemDTO;
import com.fiap.pedidoapp.infrastructure.pedido.controllers.dto.NovoPedidoDTO;
import com.fiap.pedidoapp.infrastructure.pedido.persistence.entity.ItemEntity;
import com.fiap.pedidoapp.infrastructure.pedido.persistence.entity.PedidoEntity;
import com.fiap.pedidoapp.infrastructure.pedido.persistence.entity.StatusPedidoEntity;
import com.fiap.pedidoapp.infrastructure.produto.controllers.dto.ProdutoResponse;
import com.fiap.pedidoapp.infrastructure.produto.persistence.entity.ProdutoEntity;

public class TestHelper {

    public static NovoPedidoDTO getNovoPedidoDTO() {
        return NovoPedidoDTO.builder()
                .itens(getItensDTO())
                .build();
    }

    public static List<ItemDTO> getItensDTO() {
        return Arrays.asList(
                ItemDTO.builder()
                        .codigoProduto(123)
                        .quantidade(1)
                        .build(),
                ItemDTO.builder()
                        .codigoProduto(124)
                        .quantidade(1)
                        .build());
    }

    public static List<Item> getItensPedido() {
        return Arrays.asList(
                Item.builder()
                        .idItem("123")
                        .produto(getProduto())
                        .quantidade(1)
                        .build(),
                Item.builder()
                        .idItem("124")
                        .produto(getProduto())
                        .quantidade(1)
                        .build());
    }

    public static Produto getProduto() {
        return Produto.builder()
                .idProduto(123)
                .nome("")
                .descricao("")
                .valor(BigDecimal.valueOf(100))
                .ativo(true)
                .categoria(Categoria.builder().build())
                .build();
    }

    public static Pedido getPedido() {
        return Pedido.builder()
                .idPedido(123)
                .statusPedido(StatusPedido.builder().idStatusPedido(1).descricao("Recebido").build())
                .cliente(Cliente.builder().build())
                .valorTotal(BigDecimal.valueOf(250))
                .itens(TestHelper.getItensPedido())
                .data(LocalDateTime.now())
                .build();
    }

    public static ProdutoResponse getProdutoResponse() {
        return ProdutoResponse.builder()
                .idProduto(123)
                .nome("nome")
                .descricao("descricao")
                .valor(BigDecimal.valueOf(100))
                .ativo(true)
                .categoria(Categoria.builder().build())
                .build();
    }

    public static PedidoEntity getPedidoEntity() {
        return PedidoEntity.builder()
                .idPedido(123)
                .itens(List.of(ItemEntity.builder()
                                .idItem("123")
                                .produto(ProdutoEntity.builder().build())
                                .quantidade(1)
                                .build()))
                .cliente(ClienteEntity.builder().build())
                .statusPedido(StatusPedidoEntity.builder().build())
                .valorTotal(BigDecimal.valueOf(100))
                .data(LocalDateTime.now())
                .build();
    }
}
