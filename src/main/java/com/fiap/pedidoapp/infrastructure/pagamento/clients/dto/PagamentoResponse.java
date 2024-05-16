package com.fiap.pedidoapp.infrastructure.pagamento.clients.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagamentoResponse {

    private String id;
    private Integer idPedido;
    private BigDecimal valor;
    private String statusPagamento;
    private LocalDateTime data;

}
