package com.fiap.pedidoapp.infrastructure.pedido.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.pedidoapp.application.pedido.usecases.AtualizarStatusPedido;
import com.fiap.pedidoapp.domain.pedido.enums.StatusPedidoEnum;
import com.fiap.pedidoapp.infrastructure.pedido.listener.dto.PagamentoResponse;

import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PedidoListener {

    Logger logger = LoggerFactory.getLogger(PedidoListener.class);

    private final AtualizarStatusPedido atualizarStatusPedido;

    private final  ObjectMapper objectMapper;

    @SqsListener("${aws.sqs.status.pedido.uri}")
    public void receiveMessage(String json) throws JsonProcessingException {
        var pagamentoResponse = objectMapper.readValue(json,
        PagamentoResponse.class);
        atualizarStatusPedido.atualizarStatusPedido(pagamentoResponse.getIdPedido(), StatusPedidoEnum.PAGO.getDescricao());
        logger.info("PagamentoResponse: {}", pagamentoResponse);
    }

}
