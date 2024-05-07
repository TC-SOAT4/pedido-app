package com.fiap.pedidoapp.infrastructure.cliente.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.pedidoapp.infrastructure.cliente.persistence.entity.ClienteEntity;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer>{

    public ClienteEntity findByCpf(String cpf);
    
}
