package com.fiap.pedidoapp.domain.pedido.entity;

import com.fiap.pedidoapp.domain.produto.entity.Produto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Item {

    private String idItem;

    private Produto produto;

    private Pedido pedido;

    private Integer quantidade;

}