package com.fiap.pedidoapp.infrastructure.pedido.listener.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusPedidoDTO {

    private Integer idPedido;
    private String status;
    private String taskApp;

}
