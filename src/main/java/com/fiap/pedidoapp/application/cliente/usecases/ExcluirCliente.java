package com.fiap.pedidoapp.application.cliente.usecases;

import com.fiap.pedidoapp.domain.cliente.entity.Cliente;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fiap.pedidoapp.application.cliente.gateways.ClienteGateway;

@Service
public class ExcluirCliente {

    private final ClienteGateway clienteGateway;

    public ExcluirCliente(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    @Transactional
    public void execute(Integer id) {
        Cliente cliente = clienteGateway.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        clienteGateway.delete(cliente);
    }
}
