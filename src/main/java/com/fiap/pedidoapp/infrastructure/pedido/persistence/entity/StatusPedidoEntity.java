package com.fiap.pedidoapp.infrastructure.pedido.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "StatusPedido")
public class StatusPedidoEntity {

    public static final Integer RECEBIDO = 1;
    public static final Integer PAGO = 2;
    public static final Integer EM_PREPARACAO = 3;
    public static final Integer PRONTO = 4;
    public static final Integer FINALIZADO = 5;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStatusPedido;

    private String descricao;

}
