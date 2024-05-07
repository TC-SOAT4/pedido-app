package com.fiap.pedidoapp.application.cliente.gateways;

import com.fiap.pedidoapp.domain.cliente.entity.Cliente;

public interface ClienteGateway {

    public Cliente buscarPorCpf(String cpf);

    public Cliente cadatrarCliente(Cliente cliente);
}
