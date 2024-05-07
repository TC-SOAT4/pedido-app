package com.fiap.pedidoapp.infrastructure.pedido.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.pedidoapp.infrastructure.pedido.persistence.entity.StatusPedidoEntity;

public interface StatusPedidoRepository extends JpaRepository<StatusPedidoEntity, Integer> {

    public Optional<StatusPedidoEntity> findByDescricao(String descricao);
    
}
