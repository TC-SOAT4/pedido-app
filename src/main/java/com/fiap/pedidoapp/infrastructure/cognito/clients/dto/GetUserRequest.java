package com.fiap.pedidoapp.infrastructure.cognito.clients.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class GetUserRequest {

    @JsonProperty("AccessToken")
    private String accessToken;

}
