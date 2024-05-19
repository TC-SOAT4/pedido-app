package com.fiap.pedidoapp.infrastructure.pedido.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fiap.pedidoapp.domain.pedido.entity.Pedido;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class PedidoPagoDTO {

    private Integer codigoPedido;
    private boolean pedidoPago;

    public PedidoPagoDTO(Pedido pedido) {
        this.codigoPedido = pedido.getIdPedido();
        this.pedidoPago = false;
    }

}
