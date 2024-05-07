package com.fiap.pedidoapp.infrastructure.cliente.gateways;

import com.fiap.pedidoapp.application.cliente.gateways.ClienteGateway;
import com.fiap.pedidoapp.domain.cliente.entity.Cliente;
import com.fiap.pedidoapp.infrastructure.cliente.persistence.entity.ClienteEntity;
import com.fiap.pedidoapp.infrastructure.cliente.persistence.repository.ClienteRepository;

public class ClienteRepositoryGateway implements ClienteGateway {

    private final ClienteRepository clienteRepository;

    public ClienteRepositoryGateway(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente cadatrarCliente(Cliente cliente) {
        ClienteEntity novoCliente = ClienteEntity.builder()
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .email(cliente.getEmail())
                .ativo(Boolean.TRUE)
                .build();

        novoCliente = clienteRepository.save(novoCliente);

        return new Cliente(novoCliente);
    }

    @Override
    public Cliente buscarPorCpf(String cpf) {
        ClienteEntity clienteEntity = clienteRepository.findByCpf(cpf);
        return clienteEntity != null ? new Cliente(clienteEntity) : null;
    }

}
