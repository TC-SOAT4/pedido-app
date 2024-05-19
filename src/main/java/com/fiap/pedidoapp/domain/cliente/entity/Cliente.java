package com.fiap.pedidoapp.domain.cliente.entity;

import com.fiap.pedidoapp.infrastructure.cliente.persistence.entity.ClienteEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {

    private Integer idCliente;

    private String nome;
    private String cpf;
    private String email;
    private Boolean ativo;

    public Cliente(ClienteEntity clienteEntity) {
        this.idCliente = clienteEntity.getIdCliente();
        this.nome = clienteEntity.getNome();
        this.cpf = clienteEntity.getCpf();
        this.email = clienteEntity.getEmail();
        this.ativo = clienteEntity.getAtivo();
    }

}
