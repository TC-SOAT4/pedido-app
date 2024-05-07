package com.fiap.pedidoapp.infrastructure.cliente.controllers.dto;

import com.fiap.pedidoapp.domain.cliente.entity.Cliente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDTO {

    private Integer idCliente;
    private String nome;
    private String cpf;
    private String email;


    
    public ClienteResponseDTO(Cliente cliente){
        this.idCliente = cliente.getIdCliente();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.email = cliente.getEmail();
    }
}
