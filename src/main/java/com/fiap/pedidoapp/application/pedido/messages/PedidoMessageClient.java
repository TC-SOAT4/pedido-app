package com.fiap.pedidoapp.application.pedido.messages;

import java.math.BigDecimal;

import com.fiap.pedidoapp.infrastructure.pedido.schedule.dto.ResumoPreparacaoPedidoDTO;

public interface PedidoMessageClient {

    public void enviarPedidoParaFilaDeProducao(ResumoPreparacaoPedidoDTO resumoPreparacaoPedidoDTO);

    public void enviarPedidoParaFilaDePagamento(Integer idPedido, BigDecimal valor);

}
