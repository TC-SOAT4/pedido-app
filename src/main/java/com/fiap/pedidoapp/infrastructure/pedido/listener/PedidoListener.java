package com.fiap.pedidoapp.infrastructure.pedido.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.pedidoapp.application.pedido.usecases.AtualizarStatusPedido;
import com.fiap.pedidoapp.application.pedido.usecases.EnviarPedidoPagoParaPreparacao;
import com.fiap.pedidoapp.domain.pedido.enums.StatusPedidoEnum;
import com.fiap.pedidoapp.infrastructure.pedido.listener.dto.PagamentoResponse;

import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PedidoListener {

    Logger logger = LoggerFactory.getLogger(PedidoListener.class);

    private final AtualizarStatusPedido atualizarStatusPedido;

    private final EnviarPedidoPagoParaPreparacao enviarPedidoPagoParaPreparacao;

    private final ObjectMapper objectMapper;

    @SqsListener("${aws.sqs.in.pedido.pago.name}")
    public void receiveMessagePedidoPago(String json) throws JsonProcessingException {
        var pagamentoResponse = objectMapper.readValue(json,
                PagamentoResponse.class);
        atualizarStatusPedido.atualizarStatusPedido(pagamentoResponse.getIdPedido(),
                StatusPedidoEnum.PAGO.getDescricao());
        logger.info("Pagamento recebido: {}", pagamentoResponse);

        enviarPedidoPagoParaPreparacao.enviar(pagamentoResponse.getIdPedido());
    }

    @SqsListener("${aws.sqs.in.pedido.pronto.name}")
    public void receiveMessagePedidoPronto(String json) throws JsonProcessingException {
        var idPedido = objectMapper.readValue(json,
                Integer.class);
        atualizarStatusPedido.atualizarStatusPedido(idPedido, StatusPedidoEnum.PRONTO.getDescricao());
        logger.info("Pagamento pronto: {}", idPedido);
    }

}
