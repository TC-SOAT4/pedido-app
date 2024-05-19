package com.fiap.pedidoapp.infrastructure.pedido.controllers.dto;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
public class ResumoPedidoDTO {

    private Integer codigoPedido;
    private String nomeCliente;
    private BigDecimal valorTotal;
    private List<ResumoItemDTO> itens;
    private String statusPedido;
    private String dataHoraPedido;

    public ResumoPedidoDTO(Pedido pedido) {
        this.codigoPedido = pedido.getIdPedido();
        this.statusPedido = pedido.getStatusPedido().getDescricao();
        this.nomeCliente = pedido.getCliente() != null ? pedido.getCliente().getNome() : null;
        this.valorTotal = pedido.getValorTotal();
        this.itens = pedido.getItens().stream().map(item -> new ResumoItemDTO(item)).toList();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM HH:mm");
        this.dataHoraPedido = pedido.getData().format(formatter);
    }

}
