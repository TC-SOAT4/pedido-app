package com.fiap.pedidoapp.infrastructure.pedido.messages;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.pedidoapp.TestHelper;
import com.fiap.pedidoapp.infrastructure.pedido.schedule.dto.ResumoPreparacaoPedidoDTO;

import io.awspring.cloud.sqs.operations.SqsTemplate;

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
        assertNotNull(dto);
        assertNotNull(dto.getItens());
        assertNotNull(dto.getIdPedido());
    }

}