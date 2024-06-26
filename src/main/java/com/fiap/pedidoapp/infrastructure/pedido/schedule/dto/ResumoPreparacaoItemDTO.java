package com.fiap.pedidoapp.infrastructure.pedido.schedule.dto;

import com.fiap.pedidoapp.domain.pedido.entity.Item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResumoPreparacaoItemDTO {

    private String nome;
    private Integer quantidade;

    public ResumoPreparacaoItemDTO(Item item) {
        this.nome = item.getProduto().getNome();
        this.quantidade = item.getQuantidade();
    }

}
