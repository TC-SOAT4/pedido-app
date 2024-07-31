package com.fiap.pedidoapp.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fiap.pedidoapp.application.cliente.usecases.BuscarClientePorCpf;
import com.fiap.pedidoapp.application.pedido.gateways.PedidoGateway;
import com.fiap.pedidoapp.application.pedido.usecases.AtualizarStatusPedido;
import com.fiap.pedidoapp.application.pedido.usecases.BuscarPedidoPorId;
import com.fiap.pedidoapp.application.pedido.usecases.ListarPedidos;
import com.fiap.pedidoapp.application.pedido.usecases.RealizarCheckout;
import com.fiap.pedidoapp.application.pedido.usecases.RealizarPagamento;
import com.fiap.pedidoapp.application.produto.usecases.BuscarProdutoPorCodigo;
import com.fiap.pedidoapp.infrastructure.pedido.gateways.PedidoRepositoryGateway;
import com.fiap.pedidoapp.infrastructure.pedido.persistence.repository.PedidoRepository;
import com.fiap.pedidoapp.infrastructure.pedido.persistence.repository.StatusPedidoRepository;

@Configuration
public class PedidoConfig {

    @Bean
    RealizarCheckout realizarCheckout(PedidoGateway pedidoGateway, RealizarPagamento realizarPagamento) {
        return new RealizarCheckout(pedidoGateway, realizarPagamento);
    }

    @Bean
    ListarPedidos listarPedidos(PedidoGateway pedidoGateway) {
        return new ListarPedidos(pedidoGateway);
    }

    @Bean
    BuscarPedidoPorId buscarPedidoPorId(PedidoGateway pedidoGateway) {
        return new BuscarPedidoPorId(pedidoGateway);
    }

    @Bean
    AtualizarStatusPedido atualizarStatusPedido(PedidoGateway pedidoGateway) {
        return new AtualizarStatusPedido(pedidoGateway);
    }

    @Bean
    PedidoGateway pedidoGateway(BuscarClientePorCpf buscarClientePorCpf,
            BuscarProdutoPorCodigo buscarProdutoPorCodigo,
            PedidoRepository pedidoRepository,
            StatusPedidoRepository statusPedidoRepository) {
        return new PedidoRepositoryGateway(buscarClientePorCpf, buscarProdutoPorCodigo, pedidoRepository,
                statusPedidoRepository);
    }

}
