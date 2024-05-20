package com.fiap.pedidoapp.infrastructure.pagamento.clients;

import java.math.BigDecimal;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fiap.pedidoapp.application.pagamento.clients.PagamentoClient;
import com.fiap.pedidoapp.infrastructure.pagamento.clients.dto.PagamentoResponse;
import com.fiap.pedidoapp.infrastructure.pagamento.clients.dto.RealizarPagamentoRequest;

public class PagamentoRestClient implements PagamentoClient {

    @Value("${uri.api.pagamento}")
    private String uriApiPagamento;

    private final RestTemplate restTemplate;

    public PagamentoRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public PagamentoResponse realizarPagamento(Integer idPedido, BigDecimal valor) {

        try {
            RealizarPagamentoRequest request = new RealizarPagamentoRequest(idPedido, valor);
            restTemplate.setMessageConverters(Collections.singletonList(new MappingJackson2HttpMessageConverter()));
            ResponseEntity<PagamentoResponse> response = restTemplate.postForEntity(uriApiPagamento,
                    new HttpEntity<>(request), PagamentoResponse.class);

            return  response.getBody() != null ? response.getBody() : null;
        } catch (Exception e) {
            return null;
        }
    }

}
