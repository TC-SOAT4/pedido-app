package com.fiap.pedidoapp.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fiap.pedidoapp.application.cliente.gateways.ClienteGateway;
import com.fiap.pedidoapp.application.cliente.usecases.BuscarClientePorCpf;
import com.fiap.pedidoapp.application.cliente.usecases.CadastrarCliente;
import com.fiap.pedidoapp.application.cliente.usecases.CadastrarClientePeloToken;
import com.fiap.pedidoapp.application.cliente.usecases.InativarCliente;
import com.fiap.pedidoapp.application.cognito.clients.CognitoClient;
import com.fiap.pedidoapp.infrastructure.cliente.gateways.ClienteRepositoryGateway;
import com.fiap.pedidoapp.infrastructure.cliente.persistence.repository.ClienteRepository;
import com.fiap.pedidoapp.infrastructure.cognito.clients.CognitoRestClient;

@Configuration
public class ClienteConfig {

    @Bean
    BuscarClientePorCpf buscarClientePorCpf(ClienteGateway clienteGateway) {
        return new BuscarClientePorCpf(clienteGateway);
    }

    @Bean
    CadastrarCliente cadastrarCliente(ClienteGateway clienteGateway) {
        return new CadastrarCliente(clienteGateway);
    }

    @Bean
    InativarCliente inativarCliente(ClienteGateway clienteGateway) {
        return new InativarCliente(clienteGateway);
    }

    @Bean
    ClienteGateway clienteGateway(ClienteRepository clienteRepository) {
        return new ClienteRepositoryGateway(clienteRepository);
    }

    @Bean
    CognitoClient cognitoClient(RestTemplate restTemplate) {
        return new CognitoRestClient(restTemplate);
    }

    @Bean
    CadastrarClientePeloToken cadastrarClientePeloToken(ClienteGateway clienteGateway, CognitoClient cognitoClient) {
        return new CadastrarClientePeloToken(clienteGateway, cognitoClient);
    }

}
