package com.fiap.pedidoapp.infrastructure.pedido.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.fiap.pedidoapp.application.pedido.usecases.EnviarPedidosPagosParaPreparacao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
@ConditionalOnProperty(name = "envio.pedido.producao.enabled", havingValue = "true", matchIfMissing = false)
public class FilaPedidoSchedule {

    Logger logger = LoggerFactory.getLogger(FilaPedidoSchedule.class);

    private final EnviarPedidosPagosParaPreparacao enviarPedidosPagosParaPreparacao;

    public void enviarPedidoParaFila() {
        logger.info("Iniciando envio");
        enviarPedidosPagosParaPreparacao.enviar();
    }

}
