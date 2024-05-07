package com.fiap.pedidoapp.application.pedido.usecases;

import java.time.LocalDateTime;
import java.util.List;

import com.fiap.pedidoapp.application.pedido.gateways.PedidoGateway;
import com.fiap.pedidoapp.domain.cliente.entity.Cliente;
import com.fiap.pedidoapp.domain.pedido.entity.Item;
import com.fiap.pedidoapp.domain.pedido.entity.Pedido;
import com.fiap.pedidoapp.domain.produto.entity.Produto;
import com.fiap.pedidoapp.infrastructure.pedido.controllers.dto.ItemDTO;
import com.fiap.pedidoapp.infrastructure.pedido.controllers.dto.NovoPedidoDTO;
import com.fiap.pedidoapp.infrastructure.pedido.controllers.dto.ResumoPedidoDTO;

public class RealizarCheckout {

    private final PedidoGateway pedidoGateway;

    public RealizarCheckout(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    public ResumoPedidoDTO checkout(NovoPedidoDTO novoPedidoDTO) {
      return checkout(novoPedidoDTO, null);
    }

    public ResumoPedidoDTO checkout(NovoPedidoDTO novoPedidoDTO, String cpf) {
        Pedido pedido = Pedido.builder()
                .data(LocalDateTime.now())
                .itens(montarListaDeItens(novoPedidoDTO.getItens()))
                .cliente(cpf != null ? Cliente.builder().cpf(cpf).build() : null)
                .build();
        pedido = pedidoGateway.checkout(pedido);

        return new ResumoPedidoDTO(pedido);
    }

    private List<Item> montarListaDeItens(List<ItemDTO> itens) {
        return itens.stream().map(item -> 
             Item.builder().produto(Produto.builder().idProduto(item.getCodigoProduto()).build())
                    .quantidade(item.getQuantidade()).build()
        ).toList();
    }
}
