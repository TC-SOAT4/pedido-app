package com.fiap.pedidoapp.infrastructure.cognito.clients.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;

@Data
public class GetUserResponse {

    @JsonAlias("Username")
    private String username;

    @JsonAlias("UserAttributes")
    private List<UserAttributes> userAttributes;

}
