package com.fiap.pedidoapp.infrastructure.pedido.messages;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.pedidoapp.application.pedido.messages.PedidoMessageClient;
import com.fiap.pedidoapp.infrastructure.pedido.schedule.dto.ResumoPreparacaoPedidoDTO;

import io.awspring.cloud.sqs.operations.SqsTemplate;

@Component
public class PedidoMessageSqsClient implements PedidoMessageClient {

    private SqsTemplate sqsTemplate;

    private ObjectMapper objectMapper;

    @Value("${aws.sqs.uri}")
    private String endpoint;

    public PedidoMessageSqsClient(SqsTemplate sqsTemplate, ObjectMapper objectMapper) {
        this.sqsTemplate = sqsTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void enviarPedidoParaFilaDeProducao(ResumoPreparacaoPedidoDTO resumo) {
        try {
            String json = objectMapper.writeValueAsString(resumo);
            sqsTemplate.send(endpoint, MessageBuilder.withPayload(json).build());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
