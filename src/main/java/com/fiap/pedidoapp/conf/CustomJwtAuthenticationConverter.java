package com.fiap.pedidoapp.conf;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import com.fiap.pedidoapp.application.cliente.usecases.CadastrarClientePeloToken;

import lombok.RequiredArgsConstructor;



@Component
@RequiredArgsConstructor
public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private static final String COGNITO_GROUPS = "cognito:groups";
    private static final String COGNITO_USERNAME = "username";

    private final CadastrarClientePeloToken cadastrarClientePeloToken;

    @Override
    public AbstractAuthenticationToken convert(Jwt source) {

        String username = (String) source.getClaims().get(COGNITO_USERNAME);
        cadastrarClientePeloToken.cadastrarSeNaoCadastrado(username, source.getTokenValue());

        @SuppressWarnings("unchecked")
        List<String> roles = (List<String>) source.getClaims().get(COGNITO_GROUPS);
        System.out.println(source.getClaims().toString());
        
        if(roles == null || roles.isEmpty())
            return new JwtAuthenticationToken(source, Arrays.asList(new SimpleGrantedAuthority("ROLE_CLIENTE")), username);

        // Create authorities from roles.
        List<SimpleGrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
                
        return new JwtAuthenticationToken(source, authorities, username);
  
    }
}
