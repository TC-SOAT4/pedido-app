package com.fiap.pedidoapp.infrastructure.pedido.controllers;

import com.fiap.pedidoapp.domain.pedido.entity.Item;
import com.fiap.pedidoapp.domain.produto.entity.Categoria;
import com.fiap.pedidoapp.domain.produto.entity.Produto;
import com.fiap.pedidoapp.infrastructure.pedido.controllers.dto.ItemDTO;
import com.fiap.pedidoapp.infrastructure.pedido.controllers.dto.NovoPedidoDTO;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

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

}
