package com.fiap.pedidoapp.infrastructure.pedido.schedule.dto;

import com.fiap.pedidoapp.domain.pedido.entity.Item;

import lombok.Data;

@Data
public class ResumoPreparacaoItemDTO {

    private String nome;
    private Integer quantidade;

    public ResumoPreparacaoItemDTO(Item item) {
        this.nome = item.getProduto().getNome();
        this.quantidade = item.getQuantidade();
    }

}
