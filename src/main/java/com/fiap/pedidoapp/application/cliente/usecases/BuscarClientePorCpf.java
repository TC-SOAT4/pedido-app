package com.fiap.pedidoapp.application.cliente.usecases;

import com.fiap.pedidoapp.application.cliente.gateways.ClienteGateway;
import com.fiap.pedidoapp.domain.cliente.entity.Cliente;
import com.fiap.pedidoapp.infrastructure.cliente.controllers.dto.ClienteResponseDTO;

public class BuscarClientePorCpf {

    private final ClienteGateway clienteGateway;

    public BuscarClientePorCpf(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public ClienteResponseDTO buscarPorCpf(String cpf) {
        Cliente cliente = clienteGateway.buscarPorCpf(cpf);
        if (cliente == null || !Boolean.TRUE.equals(cliente.getAtivo())) {
            return null;
        }
        return new ClienteResponseDTO(cliente);
    }
}
