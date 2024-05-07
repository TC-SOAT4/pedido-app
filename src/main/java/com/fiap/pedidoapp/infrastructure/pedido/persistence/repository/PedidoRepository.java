package com.fiap.pedidoapp.infrastructure.pedido.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.pedidoapp.infrastructure.pedido.persistence.entity.PedidoEntity;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Integer> {

    public List<PedidoEntity> findAllByStatusPedidoIdStatusPedido(Integer idStatusPedido);
    
}
