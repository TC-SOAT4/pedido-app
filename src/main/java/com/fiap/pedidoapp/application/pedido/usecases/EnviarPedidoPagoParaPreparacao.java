package com.fiap.pedidoapp.application.pedido.usecases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fiap.pedidoapp.application.pedido.gateways.PedidoGateway;
import com.fiap.pedidoapp.application.pedido.messages.PedidoMessageClient;
import com.fiap.pedidoapp.domain.pedido.enums.StatusPedidoEnum;
import com.fiap.pedidoapp.infrastructure.pedido.schedule.dto.ResumoPreparacaoPedidoDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class EnviarPedidoPagoParaPreparacao {

    Logger logger = LoggerFactory.getLogger(EnviarPedidoPagoParaPreparacao.class);


    private final PedidoGateway pedidoGateway;

    private final PedidoMessageClient pedidoMessageClient;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { RuntimeException.class })
    public void enviar(Integer idPedido) {
        var pedido = pedidoGateway.buscarPorId(idPedido);
        var resumoPedido = new ResumoPreparacaoPedidoDTO(pedido);
        pedidoGateway.atualizarStatusPedido(resumoPedido.getIdPedido(), StatusPedidoEnum.EM_PREPARACAO.getCodigo());
        pedidoMessageClient.enviarPedidoParaFilaDeProducao(resumoPedido);
        logger.info("Pedido enviado para produção: {}", resumoPedido);
    }

}
