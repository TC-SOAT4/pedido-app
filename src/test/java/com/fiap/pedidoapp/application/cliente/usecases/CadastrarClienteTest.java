package com.fiap.pedidoapp.application.cliente.usecases;

import com.fiap.pedidoapp.TestHelper;
import com.fiap.pedidoapp.application.cliente.gateways.ClienteGateway;
import com.fiap.pedidoapp.domain.cliente.entity.Cliente;
import com.fiap.pedidoapp.infrastructure.cliente.controllers.dto.CadastroClienteRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CadastrarClienteTest {
    @Mock
    private ClienteGateway clienteGateway;

    @InjectMocks
    private CadastrarCliente cadastrarCliente;

    @Test
    void cadatrarCliente() {
        CadastroClienteRequestDTO clienteCadastro = new CadastroClienteRequestDTO("nome", "12345678912");

        Cliente cliente = Cliente.builder()
                .nome(clienteCadastro.getNome())
                .cpf(clienteCadastro.getCpf())
                .build();

        when(clienteGateway.cadatrarCliente(any(Cliente.class))).thenReturn(cliente);

        var result = cadastrarCliente.cadatrarCliente(clienteCadastro);
    }
}