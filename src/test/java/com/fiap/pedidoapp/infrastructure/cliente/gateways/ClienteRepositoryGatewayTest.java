package com.fiap.pedidoapp.infrastructure.cliente.gateways;

import com.fiap.pedidoapp.infrastructure.cliente.persistence.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class ClienteRepositoryGatewayTest {
    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteRepositoryGateway clienteRepositoryGateway;

//    @Test
    void cadatrarCliente() {
        var result = clienteRepositoryGateway.cadatrarCliente(null);
    }

    @Test
    void buscarPorCpf() {
        var result = clienteRepositoryGateway.buscarPorCpf("12345678912");
    }
}