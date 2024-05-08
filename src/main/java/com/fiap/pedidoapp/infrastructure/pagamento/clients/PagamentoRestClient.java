package com.fiap.pedidoapp.infrastructure.pagamento.clients;

import java.math.BigDecimal;

import org.springframework.web.client.RestTemplate;

import com.fiap.pedidoapp.application.pagamento.clients.PagamentoClient;

public class PagamentoRestClient implements PagamentoClient{

    public static final String URI_PAGAMENTO = "localhost:8082/pagamento-app/api/pagamento";

     private final RestTemplate restTemplate;

    public PagamentoRestClient(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }


    @Override
    public void realizarPagamento(Integer idPedido, BigDecimal valor) {

        return;

        // RealizarPagamentoRequest request = new RealizarPagamentoRequest(idPedido, valor);

        // ResponseEntity<String> response = restTemplate.postForEntity(URI_PAGAMENTO,  new HttpEntity<>(request), String.class);
        
        // if(response.getStatusCode().is2xxSuccessful())
        //     return;

        // throw new RuntimeException("Não foi possivel realizar a tentiva de pagamento");
    }

}
