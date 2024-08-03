package com.fiap.pedidoapp.application.cliente.gateways;

import com.fiap.pedidoapp.domain.cliente.entity.Cliente;

import java.util.Optional;

public interface ClienteGateway {

    Cliente buscarPorCpf(String cpf);

    Cliente cadatrarCliente(Cliente cliente);

    Optional<Cliente> findById(Integer id);

    Cliente save(Cliente cliente);

    void delete(Cliente cliente);
}
