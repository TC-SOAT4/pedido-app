package com.fiap.pedidoapp.infrastructure.pedido.messages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.pedidoapp.TestHelper;
import com.fiap.pedidoapp.infrastructure.pedido.schedule.dto.ResumoPreparacaoPedidoDTO;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import software.amazon.eventstream.MessageBuilder;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PedidoMessageSqsClientTest {
    @Mock
    private SqsTemplate sqsTemplate;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private PedidoMessageSqsClient pedidoMessageSqsClient;

    @Test
    void enviarPedidoParaFilaDeProducao() {
        var dto = new ResumoPreparacaoPedidoDTO(TestHelper.getPedido());
    }
}