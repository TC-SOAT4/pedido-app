package com.fiap.pedidoapp.application.cognito.clients;

import com.fiap.pedidoapp.infrastructure.cognito.clients.dto.GetUserResponse;

public interface CognitoClient {

    public GetUserResponse getUser(String token);

}
