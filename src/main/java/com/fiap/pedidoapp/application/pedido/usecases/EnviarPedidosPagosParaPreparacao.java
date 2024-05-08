package com.fiap.pedidoapp.application.pedido.usecases;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fiap.pedidoapp.application.pedido.gateways.PedidoGateway;
import com.fiap.pedidoapp.infrastructure.pedido.persistence.entity.StatusPedidoEntity;

import io.awspring.cloud.sqs.operations.SqsTemplate;

@Component
public class EnviarPedidosPagosParaPreparacao {

    private final PedidoGateway pedidoGateway;

    private final SqsTemplate sqsTemplate;

    @Value("${aws.sqs.uri}")
    private String endpoint;

    public EnviarPedidosPagosParaPreparacao(PedidoGateway pedidoGateway, SqsTemplate sqsTemplate) {
        this.pedidoGateway = pedidoGateway;
        this.sqsTemplate = sqsTemplate;
    }

    @Transactional(propagation=Propagation.REQUIRED, rollbackFor={RuntimeException.class})
    public void enviar() {

         pedidoGateway.listarPedidosPagos().stream().forEach(pedido -> {
            pedidoGateway.atualizarStatusPedido(pedido.getIdPedido(), StatusPedidoEntity.EM_PREPARACAO);
            sqsTemplate.send(endpoint, MessageBuilder.withPayload(pedido).build());
         });
         
    }

}
