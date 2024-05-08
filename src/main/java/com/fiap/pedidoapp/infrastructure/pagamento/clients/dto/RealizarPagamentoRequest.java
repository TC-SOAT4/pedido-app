package com.fiap.pedidoapp.infrastructure.pagamento.clients.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RealizarPagamentoRequest {

    private Integer idPedido; 
    private BigDecimal valor;

}
