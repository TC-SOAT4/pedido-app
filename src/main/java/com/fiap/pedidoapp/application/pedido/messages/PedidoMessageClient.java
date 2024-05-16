package com.fiap.pedidoapp.application.pedido.messages;

import com.fiap.pedidoapp.infrastructure.pedido.schedule.dto.ResumoPreparacaoPedidoDTO;

public interface PedidoMessageClient {

    public void enviarPedidoParaFilaDeProducao(ResumoPreparacaoPedidoDTO resumoPreparacaoPedidoDTO);

}
