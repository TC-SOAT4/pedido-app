package com.fiap.pedidoapp.infrastructure.cognito.clients;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fiap.pedidoapp.application.cognito.clients.CognitoClient;
import com.fiap.pedidoapp.infrastructure.cognito.clients.dto.GetUserRequest;
import com.fiap.pedidoapp.infrastructure.cognito.clients.dto.GetUserResponse;

public class CognitoRestClient implements CognitoClient {

    public static final MediaType MEDIA_TYPE_X_AMZ_JSON_1_1 = MediaType.valueOf("application/x-amz-json-1.1");
    public static final String X_AMZ_TARGET = "X-Amz-Target";
    public static final String AWS_COGNITO_IDENTITY_PROVIDE_SERVICE_GETUSER = "AWSCognitoIdentityProviderService.GetUser";
    public static final String URI_COGNITO = "https://cognito-idp.us-east-1.amazonaws.com";

    private final RestTemplate restTemplate;

    public CognitoRestClient(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public GetUserResponse getUser(String token) {

        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MEDIA_TYPE_X_AMZ_JSON_1_1);
        headers.add(X_AMZ_TARGET, AWS_COGNITO_IDENTITY_PROVIDE_SERVICE_GETUSER);

        GetUserRequest request = GetUserRequest.builder().accessToken(token).build();

        restTemplate.setMessageConverters(getConverterForMediaTypeAmzJson());

        ResponseEntity<GetUserResponse> userResponse = restTemplate.postForEntity(URI_COGNITO,
                new HttpEntity<>(request, headers), GetUserResponse.class);

        return userResponse.getBody();
    }

    public List<HttpMessageConverter<?>> getConverterForMediaTypeAmzJson() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(List.of(MediaType.ALL, MEDIA_TYPE_X_AMZ_JSON_1_1));
        messageConverters.add(converter);
        return messageConverters;
    }

}
