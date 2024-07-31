package com.fiap.pedidoapp.infrastructure.pedido.messages.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RealizarPagamentoRequest {

    private Integer idPedido; 
    private BigDecimal valor;

}
