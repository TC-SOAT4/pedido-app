package com.fiap.pedidoapp.application.cliente.usecases;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fiap.pedidoapp.application.cliente.gateways.ClienteGateway;
import com.fiap.pedidoapp.application.cognito.clients.CognitoClient;
import com.fiap.pedidoapp.infrastructure.cognito.clients.dto.GetUserResponse;
import com.fiap.pedidoapp.infrastructure.cognito.clients.dto.UserAttributes;

@ExtendWith(SpringExtension.class)
class CadastrarClientePeloTokenTest {
    @Mock
    private ClienteGateway clienteGateway;

    @Mock
    private CognitoClient cognitoClient;

    @InjectMocks
    private CadastrarClientePeloToken cadastrarClientePeloToken;

    @Test
    public void cadastrarSeNaoCadastrado() {
        UserAttributes userAttributes = new UserAttributes();
        userAttributes.setName("name");
        userAttributes.setValue("value");
        List<UserAttributes> userAttributesList = List.of(userAttributes);
        var userResponse = new GetUserResponse();
        userResponse.setUsername("username");
        userResponse.setUserAttributes(userAttributesList);
        when(cognitoClient.getUser(anyString())).thenReturn(userResponse);
        cadastrarClientePeloToken.cadastrarSeNaoCadastrado("username", "token");
    }
}