package com.fiap.pedidoapp.application.pedido.usecases;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.pedidoapp.application.pedido.gateways.PedidoGateway;
import com.fiap.pedidoapp.infrastructure.pedido.enums.StatusPedidoEnum;
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
         ObjectMapper objectMapper = new ObjectMapper();

        var lista = pedidoGateway.listarPedidosPagos();

        if( lista.isEmpty())
            return;

        lista.stream().map(ResumoPreparacaoPedidoDTO::new).forEach(resumoPedido -> {
            pedidoGateway.atualizarStatusPedido(resumoPedido.getIdPedido(), StatusPedidoEnum.EM_PREPARACAO.getCodigo());

            try {
                String json = objectMapper.writeValueAsString(resumoPedido);
                sqsTemplate.send(endpoint, MessageBuilder.withPayload(json).build());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            
         });
         
    }

}
