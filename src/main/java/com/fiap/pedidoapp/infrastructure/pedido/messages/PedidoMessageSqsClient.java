package com.fiap.pedidoapp.infrastructure.pedido.messages;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.pedidoapp.application.pedido.messages.PedidoMessageClient;
import com.fiap.pedidoapp.infrastructure.pedido.messages.dto.RealizarPagamentoRequest;
import com.fiap.pedidoapp.infrastructure.pedido.schedule.dto.ResumoPreparacaoPedidoDTO;

import io.awspring.cloud.sqs.operations.SqsTemplate;

@Component
public class PedidoMessageSqsClient implements PedidoMessageClient {

    private SqsTemplate sqsTemplate;

    private ObjectMapper objectMapper;

    Logger logger = LoggerFactory.getLogger(PedidoMessageSqsClient.class);

    @Value("${aws.sqs.producao.uri}")
    private String endpointProducao;

    @Value("${aws.sqs.pagamento.uri}")
    private String endpointPagamento;

    public PedidoMessageSqsClient(SqsTemplate sqsTemplate, ObjectMapper objectMapper) {
        this.sqsTemplate = sqsTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void enviarPedidoParaFilaDeProducao(ResumoPreparacaoPedidoDTO resumo) {
        try {
            String json = objectMapper.writeValueAsString(resumo);
            sqsTemplate.send(endpointProducao, MessageBuilder.withPayload(json).build());
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void enviarPedidoParaFilaDePagamento(Integer idPedido, BigDecimal valor) {
        try {
            RealizarPagamentoRequest data = new RealizarPagamentoRequest(idPedido, valor);

            String json = objectMapper.writeValueAsString(data);
            sqsTemplate.send(endpointPagamento, MessageBuilder.withPayload(json).build());
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
