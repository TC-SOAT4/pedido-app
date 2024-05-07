package com.fiap.pedidoapp.application.cliente.usecases;

import java.util.List;
import java.util.Optional;

import com.fiap.pedidoapp.application.cliente.gateways.ClienteGateway;
import com.fiap.pedidoapp.application.cognito.clients.CognitoClient;
import com.fiap.pedidoapp.domain.cliente.entity.Cliente;
import com.fiap.pedidoapp.infrastructure.cognito.clients.dto.GetUserResponse;
import com.fiap.pedidoapp.infrastructure.cognito.clients.dto.UserAttributes;

public class CadastrarClientePeloToken {

    private static final String COGNITO_NAME = "name";
    private static final String COGNITO_EMAIL = "email";

    private final ClienteGateway clienteGateway;
    private final CognitoClient cognitoClient;

    public CadastrarClientePeloToken(ClienteGateway clienteGateway, CognitoClient cognitoClient) {
        this.clienteGateway = clienteGateway;
        this.cognitoClient = cognitoClient;
    }

    public void cadastrarSeNaoCadastrado(String username, String token) {

        if (clienteGateway.buscarPorCpf(username) != null)
            return;

        GetUserResponse userResponse = cognitoClient.getUser(token);

        String cpf = userResponse.getUsername();
        String nome = getAttributeByName(userResponse.getUserAttributes(), COGNITO_NAME);
        String email = getAttributeByName(userResponse.getUserAttributes(), COGNITO_EMAIL);

        Cliente cliente = Cliente.builder()
                .nome(nome).cpf(cpf).email(email).build();

        clienteGateway.cadatrarCliente(cliente);
    }

    private String getAttributeByName(List<UserAttributes> userAttributes, String attributeName) {
        Optional<UserAttributes> attribute = userAttributes.stream().filter(att -> att.getName().equals(attributeName))
                .findFirst();
        return attribute.isPresent() ? attribute.get().getValue() : null;
    }

}
