package com.fiap.pedidoapp.application.pedido.usecases;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fiap.pedidoapp.application.pedido.gateways.PedidoGateway;
import com.fiap.pedidoapp.infrastructure.pedido.persistence.entity.StatusPedidoEntity;
import com.fiap.pedidoapp.infrastructure.pedido.schedule.dto.ResumoPreparacaoPedidoDTO;

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
        var lista = pedidoGateway.listarPedidosPagos();

        if( lista.isEmpty())
            return;

        lista.stream().map(ResumoPreparacaoPedidoDTO::new).forEach(resumoPedido -> {
            pedidoGateway.atualizarStatusPedido(resumoPedido.getIdPedido(), StatusPedidoEntity.EM_PREPARACAO);
            sqsTemplate.send(endpoint, MessageBuilder.withPayload(resumoPedido).build());
         });
         
    }

}
