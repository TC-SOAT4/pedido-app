package com.fiap.pedidoapp.infrastructure.pedido.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.pedidoapp.application.pedido.usecases.AtualizarStatusPedido;
import com.fiap.pedidoapp.application.pedido.usecases.EnviarPedidoPagoParaPreparacao;
import com.fiap.pedidoapp.domain.pedido.enums.StatusPedidoEnum;
import com.fiap.pedidoapp.infrastructure.pedido.listener.dto.StatusPedidoDTO;

import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PedidoStatusListener {

    Logger logger = LoggerFactory.getLogger(PedidoStatusListener.class);

    private final AtualizarStatusPedido atualizarStatusPedido;

    private final EnviarPedidoPagoParaPreparacao enviarPedidoPagoParaPreparacao;

    private final ObjectMapper objectMapper;

    @SqsListener("${aws.sqs.in.pedido.status.pago.name}")
    public void receiveMessage(String json) throws JsonProcessingException {
        var statusPedido = objectMapper.readValue(json,
                StatusPedidoDTO.class);

        if (statusPedido != null)
            processarStatusPedidos(statusPedido);
    }

    public void processarStatusPedidos(StatusPedidoDTO statusPedido) {
        var status = StatusPedidoEnum.getFromDescricao(statusPedido.getStatus());
        switch (status) {
            case PAGO:
                atualizarStatusParaPagoEhEnviarParaProducao(statusPedido.getIdPedido());
                break;
            case PRONTO:
                atualizarStatusParaProto(statusPedido.getIdPedido());
                break;
            default:
                break;
        }

    }

    public void atualizarStatusParaPagoEhEnviarParaProducao(Integer idPedido) {
        atualizarStatusPedido.atualizarStatusPedido(idPedido, StatusPedidoEnum.PAGO.getDescricao());
        logger.info("Pagamento recebido: {}", idPedido);
        enviarPedidoPagoParaPreparacao.enviar(idPedido);

    }

    public void atualizarStatusParaProto(Integer idPedido) {
        atualizarStatusPedido.atualizarStatusPedido(idPedido, StatusPedidoEnum.PRONTO.getDescricao());
        logger.info("Pedido pronto: {}", idPedido);
    }

}
