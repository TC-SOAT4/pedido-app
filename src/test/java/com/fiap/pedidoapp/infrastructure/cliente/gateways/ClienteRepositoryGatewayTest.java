package com.fiap.pedidoapp.infrastructure.cliente.gateways;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fiap.pedidoapp.domain.cliente.entity.Cliente;
import com.fiap.pedidoapp.infrastructure.cliente.persistence.entity.ClienteEntity;
import com.fiap.pedidoapp.infrastructure.cliente.persistence.repository.ClienteRepository;

@ExtendWith(SpringExtension.class)
class ClienteRepositoryGatewayTest {
    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteRepositoryGateway clienteRepositoryGateway;

    @Test
    void testCadatrarCliente() {
        Cliente cliente = Cliente.builder()
                .idCliente(123)
                .nome("nome")
                .cpf("12345678912")
                .email("email@email.com")
                .ativo(true)
                .build();

        ClienteEntity entity = ClienteEntity.builder()
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .email(cliente.getEmail())
                .ativo(Boolean.TRUE)
                .build();

        when(clienteRepository.save(any(ClienteEntity.class))).thenReturn(entity);

        var result = clienteRepositoryGateway.cadatrarCliente(cliente);

        assertNotNull(result);
        assertEquals(cliente.getNome(), result.getNome());
        assertEquals(cliente.getCpf(), result.getCpf());
        assertEquals(cliente.getEmail(), result.getEmail());
        assertEquals(cliente.getAtivo(), result.getAtivo());

    }

    @Test
    void testBuscarPorCpf() {
        var cpf = "12345678912";

        when(clienteRepository.findByCpf(any(String.class))).thenReturn(ClienteEntity.builder()
                .idCliente(123)
                .nome("nome")
                .cpf("12345678912")
                .email("email@email.com")
                .ativo(true)
                .build());
        var result = clienteRepositoryGateway.buscarPorCpf(cpf);
        assertNotNull(result);
        assertEquals(cpf, result.getCpf());
    }
}
