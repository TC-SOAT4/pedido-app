package com.fiap.pedidoapp.infrastructure.cognito.clients.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserResponse {

    @JsonAlias("Username")
    private String username;

    @JsonAlias("UserAttributes")
    private List<UserAttributes> userAttributes;

}
