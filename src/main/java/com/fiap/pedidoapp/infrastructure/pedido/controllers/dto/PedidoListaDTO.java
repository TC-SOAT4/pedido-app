package com.fiap.pedidoapp.infrastructure.pedido.controllers.dto;

import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fiap.pedidoapp.domain.pedido.entity.Pedido;
import com.fiap.pedidoapp.domain.pedido.enums.StatusPedidoEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
        switch (StatusPedidoEnum.getFromDescricao(statusPedido)) {
            case PRONTO:
                return 0;
            case EM_PREPARACAO:
                return 1;
            case PAGO:
                return 2;
            case AGUARDANDO_PAGAMENTO:
                return 3;
            case RECEBIDO:
                return 4;
            default:
                return 5;
        }
    }

}
