package com.fiap.pedidoapp.infrastructure.pedido.schedule.dto;

import java.util.List;

import com.fiap.pedidoapp.domain.pedido.entity.Pedido;

import lombok.Data;

@Data
public class ResumoPreparacaoPedidoDTO {

    private Integer idPedido;
    private List<ResumoPreparacaoItemDTO> itens;

    public ResumoPreparacaoPedidoDTO(Pedido pedido) {
        this.idPedido = pedido.getIdPedido();
        this.itens = pedido.getItens().stream().map(ResumoPreparacaoItemDTO::new).toList();
    }

}
