package com.fiap.pedidoapp.application.cliente.usecases;

import com.fiap.pedidoapp.application.cliente.gateways.ClienteGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class BuscarClientePorCpfTest {
    @Mock
    private ClienteGateway clienteGateway;

    @InjectMocks
    private BuscarClientePorCpf buscarClientePorCpf;

    @Test
    void buscarPorCpf() {
        var result = buscarClientePorCpf.buscarPorCpf("12345678912");
    }
}