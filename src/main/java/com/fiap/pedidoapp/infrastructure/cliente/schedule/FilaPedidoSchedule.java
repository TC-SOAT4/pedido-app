package com.fiap.pedidoapp.infrastructure.cliente.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fiap.pedidoapp.application.pedido.usecases.EnviarPedidosPagosParaPreparacao;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Component
public class FilaPedidoSchedule {

    Logger logger = LoggerFactory.getLogger(FilaPedidoSchedule.class);

    private final EnviarPedidosPagosParaPreparacao enviarPedidosPagosParaPreparacao;

    @Scheduled(fixedDelay = 5000)
    public void enviarPedidoParaFila() {
        logger.info("Iniciando envio");
        enviarPedidosPagosParaPreparacao.enviar();
    }

}
