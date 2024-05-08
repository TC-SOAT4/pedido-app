package com.fiap.pedidoapp.infrastructure.pedido.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.fiap.pedidoapp.infrastructure.pedido.persistence.entity.PedidoEntity;
import com.fiap.pedidoapp.infrastructure.pedido.persistence.entity.StatusPedidoEntity;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Integer> {

    public List<PedidoEntity> findAllByStatusPedidoIdStatusPedido(Integer idStatusPedido);

    @Modifying
    @Query("UPDATE PedidoEntity pedido SET pedido.statusPedido = :statusPedido WHERE pedido.idPedido = :idPedido")
    public void updateStatusByCdPedido(Integer idPedido, StatusPedidoEntity statusPedido);
    
}
