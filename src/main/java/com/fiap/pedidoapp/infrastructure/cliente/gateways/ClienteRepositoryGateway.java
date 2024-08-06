package com.fiap.pedidoapp.infrastructure.cliente.gateways;

import com.fiap.pedidoapp.application.cliente.gateways.ClienteGateway;
import com.fiap.pedidoapp.domain.cliente.entity.Cliente;
import com.fiap.pedidoapp.infrastructure.cliente.persistence.entity.ClienteEntity;
import com.fiap.pedidoapp.infrastructure.cliente.persistence.repository.ClienteRepository;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
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
                .endereco(cliente.getEndereco())
                .telefone(cliente.getTelefone())
                .build();

        novoCliente = clienteRepository.save(novoCliente);

        return new Cliente(novoCliente);
    }

    @Override
    public Cliente buscarPorCpf(String cpf) {
        ClienteEntity clienteEntity = clienteRepository.findByCpf(cpf);
        return clienteEntity != null ? new Cliente(clienteEntity) : null;
    }

    @Override
    public Optional<Cliente> findById(Integer id) {
        return clienteRepository.findById(id).map(Cliente::new);
    }

    @Override
    public Cliente save(Cliente cliente) {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setIdCliente(cliente.getIdCliente());
        clienteEntity.setNome(cliente.getNome());
        clienteEntity.setCpf(cliente.getCpf());
        clienteEntity.setEmail(cliente.getEmail());
        clienteEntity.setAtivo(cliente.getAtivo());
        clienteEntity.setEndereco(cliente.getEndereco());
        clienteEntity.setTelefone(cliente.getTelefone());
        return new Cliente(clienteRepository.save(clienteEntity));
    }
}
