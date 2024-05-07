package com.fiap.pedidoapp.domain.pedido.entity;

import com.fiap.pedidoapp.domain.produto.entity.Produto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {

    private String idItem;

    private Produto produto;

    private Pedido pedido;

    private Integer quantidade;

}