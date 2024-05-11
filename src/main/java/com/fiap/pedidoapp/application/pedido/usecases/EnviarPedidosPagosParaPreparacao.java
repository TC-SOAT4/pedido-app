package com.fiap.pedidoapp.application.pedido.usecases;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fiap.pedidoapp.application.pedido.gateways.PedidoGateway;
import com.fiap.pedidoapp.application.pedido.messages.PedidoMessageClient;
import com.fiap.pedidoapp.domain.pedido.enums.StatusPedidoEnum;
import com.fiap.pedidoapp.infrastructure.pedido.schedule.dto.ResumoPreparacaoPedidoDTO;

@Component
public class EnviarPedidosPagosParaPreparacao {

    private final PedidoGateway pedidoGateway;

    private final PedidoMessageClient pedidoMessageClient;

    public EnviarPedidosPagosParaPreparacao(PedidoGateway pedidoGateway, PedidoMessageClient pedidoMessageClient) {
        this.pedidoGateway = pedidoGateway;
        this.pedidoMessageClient = pedidoMessageClient;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { RuntimeException.class })
    public void enviar() {
        var lista = pedidoGateway.listarPedidosPagos();

        if (lista.isEmpty())
            return;

        lista.stream().map(ResumoPreparacaoPedidoDTO::new).forEach(resumoPedido -> {
            pedidoGateway.atualizarStatusPedido(resumoPedido.getIdPedido(), StatusPedidoEnum.EM_PREPARACAO.getCodigo());
            pedidoMessageClient.enviarPedidoParaFilaDeProducao(resumoPedido);
        });

    }

}
