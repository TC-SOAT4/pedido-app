package com.fiap.pedidoapp.conf;

import com.fiap.pedidoapp.application.pedido.usecases.*;
import com.fiap.pedidoapp.infrastructure.pedido.persistence.repository.StatusPedidoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fiap.pedidoapp.application.cliente.usecases.BuscarClientePorCpf;
import com.fiap.pedidoapp.application.pedido.gateways.PedidoGateway;
import com.fiap.pedidoapp.application.produto.usecases.BuscarProdutoPorCodigo;
import com.fiap.pedidoapp.infrastructure.pedido.gateways.PedidoRepositoryGateway;
import com.fiap.pedidoapp.infrastructure.pedido.persistence.repository.PedidoRepository;
import com.fiap.pedidoapp.infrastructure.pedido.persistence.repository.StatusPagamentoRepository;

@Configuration
public class PedidoConfig {

    @Bean
    RealizarCheckout realizarCheckout(PedidoGateway pedidoGateway) {
        return new RealizarCheckout(pedidoGateway);
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
            StatusPedidoRepository statusPedidoRepository,
            StatusPagamentoRepository statusPagamentoRepository) {
        return new PedidoRepositoryGateway(buscarClientePorCpf, buscarProdutoPorCodigo, pedidoRepository,
                statusPedidoRepository, statusPagamentoRepository);
    }

}
