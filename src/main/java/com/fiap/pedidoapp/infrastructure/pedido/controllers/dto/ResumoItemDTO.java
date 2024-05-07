package com.fiap.pedidoapp.infrastructure.pedido.controllers.dto;

import java.math.BigDecimal;

import com.fiap.pedidoapp.domain.pedido.entity.Item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumoItemDTO {

    private Integer codigoProduto;
    private String nomeProduto;
    private Integer quantidade;
    private BigDecimal valor;

    public ResumoItemDTO(Item item) {
        this.codigoProduto = item.getProduto().getIdProduto();
        this.nomeProduto = item.getProduto().getNome();
        this.quantidade = item.getQuantidade();
        this.valor = item.getProduto().getValor();
    }

    public BigDecimal getValorTotal() {
        return valor.multiply(BigDecimal.valueOf(quantidade));
    }

}
