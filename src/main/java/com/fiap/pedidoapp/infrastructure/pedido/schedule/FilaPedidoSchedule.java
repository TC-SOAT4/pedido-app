package com.fiap.pedidoapp.infrastructure.pedido.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Component
public class FilaPedidoSchedule {

    Logger logger = LoggerFactory.getLogger(FilaPedidoSchedule.class);

    // private final EnviarPedidosPagosParaPreparacao enviarPedidosPagosParaPreparacao;

    //TODO iniciar envio para fila
    
    // @Scheduled(fixedDelay = 5000)
    public void enviarPedidoParaFila() {
        logger.info("Iniciando envio");
        // enviarPedidosPagosParaPreparacao.enviar();
    }

}
