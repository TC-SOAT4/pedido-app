
package com.fiap.pedidoapp.infrastructure.pagamento.clients;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import com.fiap.pedidoapp.infrastructure.pagamento.clients.dto.PagamentoResponse;

@Profile("test")
@ExtendWith(SpringExtension.class)
class PagamentoRestClientTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PagamentoRestClient pagamentoRestClient;

    @Test
    void testRealizarPagamento() {
        Integer idPedido = 123;
        BigDecimal valor = BigDecimal.valueOf(100);
        var pagamentoResponse = new PagamentoResponse();
        pagamentoResponse.setStatusPagamento("Status");

        when(restTemplate.postForEntity(any(String.class), any(), eq(PagamentoResponse.class)))
                .thenReturn(new ResponseEntity<>(pagamentoResponse, HttpStatus.OK));
        var response = pagamentoRestClient.realizarPagamento(idPedido, valor);
        assertNull(response);
    }
}
