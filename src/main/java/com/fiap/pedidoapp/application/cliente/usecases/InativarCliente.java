package com.fiap.pedidoapp.application.cliente.usecases;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fiap.pedidoapp.application.cliente.gateways.ClienteGateway;
import com.fiap.pedidoapp.domain.cliente.entity.Cliente;

@Service
public class InativarCliente {

    private final ClienteGateway clienteGateway;

    public InativarCliente(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    @Transactional
    public void execute(Integer id) {
        Cliente cliente = clienteGateway.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        cliente.setAtivo(false);
        clienteGateway.save(cliente);
    }
}
