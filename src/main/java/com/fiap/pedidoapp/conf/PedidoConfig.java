package com.fiap.pedidoapp.conf;

import com.fiap.pedidoapp.application.pedido.usecases.*;
import com.fiap.pedidoapp.infrastructure.pedido.persistence.repository.StatusPedidoRepository;

import io.awspring.cloud.sqs.operations.SqsTemplate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fiap.pedidoapp.application.cliente.usecases.BuscarClientePorCpf;
import com.fiap.pedidoapp.application.pedido.gateways.PedidoGateway;
import com.fiap.pedidoapp.application.produto.usecases.BuscarProdutoPorCodigo;
import com.fiap.pedidoapp.infrastructure.pedido.gateways.PedidoRepositoryGateway;
import com.fiap.pedidoapp.infrastructure.pedido.persistence.repository.PedidoRepository;

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
            StatusPedidoRepository statusPedidoRepository) {
        return new PedidoRepositoryGateway(buscarClientePorCpf, buscarProdutoPorCodigo, pedidoRepository,
                statusPedidoRepository);
    }

    @Bean
    EnviarPedidosPagosParaPreparacao enviarPedidosPagosParaPreparacao(PedidoGateway pedidoGateway, SqsTemplate sqsTemplate) {
        return new EnviarPedidosPagosParaPreparacao(pedidoGateway, sqsTemplate);
    }

}
