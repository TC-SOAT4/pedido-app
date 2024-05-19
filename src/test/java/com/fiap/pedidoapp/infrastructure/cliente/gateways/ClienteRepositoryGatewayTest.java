package com.fiap.pedidoapp.infrastructure.cliente.gateways;

import com.fiap.pedidoapp.TestHelper;
import com.fiap.pedidoapp.domain.cliente.entity.Cliente;
import com.fiap.pedidoapp.infrastructure.cliente.persistence.entity.ClienteEntity;
import com.fiap.pedidoapp.infrastructure.cliente.persistence.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ClienteRepositoryGatewayTest {
    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteRepositoryGateway clienteRepositoryGateway;

    @Test
    void cadatrarCliente() {
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
    }

    @Test
    void buscarPorCpf() {
        var result = clienteRepositoryGateway.buscarPorCpf("12345678912");
    }
}
