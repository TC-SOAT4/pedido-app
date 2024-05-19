package com.fiap.pedidoapp.infrastructure.cognito.clients.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAttributes {

    @JsonAlias("Name")
    private String name;

    @JsonAlias("Value")
    private String value;

}
