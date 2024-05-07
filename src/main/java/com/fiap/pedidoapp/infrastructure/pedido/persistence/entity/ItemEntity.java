package com.fiap.pedidoapp.infrastructure.pedido.persistence.entity;

import com.fiap.pedidoapp.infrastructure.produto.persistence.entity.ProdutoEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Item")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idItem;

    @ManyToOne
    @JoinColumn(name = "produtoId")
    private ProdutoEntity produto;

    @ManyToOne
    @JoinColumn(name = "pedidoId")
    private PedidoEntity pedido;

    private Integer quantidade;

}