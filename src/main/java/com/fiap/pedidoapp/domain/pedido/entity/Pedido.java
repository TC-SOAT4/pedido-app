package com.fiap.pedidoapp.domain.pedido.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fiap.pedidoapp.domain.cliente.entity.Cliente;
import com.fiap.pedidoapp.domain.produto.entity.Produto;
import com.fiap.pedidoapp.infrastructure.pedido.persistence.entity.PedidoEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pedido {

    private Integer idPedido;

    private List<Item> itens;

    private Cliente cliente;

    private StatusPedido statusPedido;

    private BigDecimal valorTotal;

    private LocalDateTime data;

    public void setPedidoNosItens() {
        itens.forEach(item -> item.setPedido(this));
    }

    public Pedido(PedidoEntity pedidoEntity) {
        this.idPedido = pedidoEntity.getIdPedido();
        this.itens = pedidoEntity.getItens().stream().map(item -> 
             Item.builder()
            .idItem(item.getIdItem())
            .produto(new Produto(item.getProduto()))
            .quantidade(item.getQuantidade())
            .build()
        ).toList();
        this.cliente = pedidoEntity.getCliente() != null ? new Cliente(pedidoEntity.getCliente()) : null;
        this.statusPedido = new StatusPedido( pedidoEntity.getStatusPedido());
        this.valorTotal = pedidoEntity.getValorTotal();
        this.data = pedidoEntity.getData();
    }

}
