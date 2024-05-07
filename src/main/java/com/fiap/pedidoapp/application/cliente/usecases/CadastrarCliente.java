package com.fiap.pedidoapp.application.cliente.usecases;

import com.fiap.pedidoapp.application.cliente.gateways.ClienteGateway;
import com.fiap.pedidoapp.domain.cliente.entity.Cliente;
import com.fiap.pedidoapp.infrastructure.cliente.controllers.dto.CadastroClienteRequestDTO;
import com.fiap.pedidoapp.infrastructure.cliente.controllers.dto.ClienteResponseDTO;

public class CadastrarCliente {

    private final ClienteGateway clienteGateway;

    public CadastrarCliente(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public ClienteResponseDTO cadatrarCliente(CadastroClienteRequestDTO clienteCadastro) {

        Cliente cliente = Cliente.builder()
                .nome(clienteCadastro.getNome()).cpf(clienteCadastro.getCpf()).build();

        cliente = clienteGateway.cadatrarCliente(cliente);
        return new ClienteResponseDTO(cliente);
    }

}
