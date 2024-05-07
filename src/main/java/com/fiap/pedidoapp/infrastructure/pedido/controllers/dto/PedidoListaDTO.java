package com.fiap.pedidoapp.infrastructure.pedido.controllers.dto;

import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fiap.pedidoapp.domain.pedido.entity.Pedido;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class PedidoListaDTO {

    private Integer codigoPedido;
    private Object nomeCliente;
    private String statusPedido;
    private String dataHoraPedido;

    public PedidoListaDTO(Pedido pedido) {
        this.codigoPedido = pedido.getIdPedido();
        this.statusPedido = pedido.getStatusPedido().getDescricao();
        this.nomeCliente = pedido.getCliente() != null ? pedido.getCliente().getNome() : null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM HH:mm");
        this.dataHoraPedido = pedido.getData().format(formatter);
    }

    public int getStatusOrder() {
        switch (statusPedido.toLowerCase()) {
            case "pronto":
                return 0;
            case "em preparação":
                return 1;
            case "recebido":
                return 2;
            default:
                return 3;
        }
    }

}
