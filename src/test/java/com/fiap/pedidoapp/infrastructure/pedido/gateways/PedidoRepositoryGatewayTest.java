package com.fiap.pedidoapp.infrastructure.pedido.gateways;

import com.fiap.pedidoapp.TestHelper;
import com.fiap.pedidoapp.application.cliente.usecases.BuscarClientePorCpf;
import com.fiap.pedidoapp.application.produto.usecases.BuscarProdutoPorCodigo;
import com.fiap.pedidoapp.infrastructure.pedido.persistence.entity.PedidoEntity;
import com.fiap.pedidoapp.infrastructure.pedido.persistence.entity.StatusPedidoEntity;
import com.fiap.pedidoapp.infrastructure.pedido.persistence.repository.PedidoRepository;
import com.fiap.pedidoapp.infrastructure.pedido.persistence.repository.StatusPedidoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PedidoRepositoryGatewayTest {
    @Mock
    private BuscarClientePorCpf buscarClientePorCpf;

    @Mock
    private StatusPedidoRepository statusPedidoRepository;

    @Mock
    private BuscarProdutoPorCodigo buscarProdutoPorCodigo;

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    PedidoRepositoryGateway pedidoRepositoryGateway;

    @Test
    void listarPedidos() {
        var result = pedidoRepositoryGateway.listarPedidos();
    }

    @Test
    void checkout() {
        when(buscarProdutoPorCodigo.buscarPorCodigo(anyInt())).thenReturn(TestHelper.getProdutoResponse());
        when(pedidoRepository.saveAndFlush(any(PedidoEntity.class))).thenReturn(TestHelper.getPedidoEntity());
        var result = pedidoRepositoryGateway.checkout(TestHelper.getPedido());
    }

    @Test
    void buscarPorId() {
        when(pedidoRepository.findById(anyInt())).thenReturn(Optional.ofNullable(TestHelper.getPedidoEntity()));
        var result = pedidoRepositoryGateway.buscarPorId(123);
    }

    @Test
    void atualizarStatusPedido() {
        when(pedidoRepository.findById(anyInt())).thenReturn(Optional.ofNullable(TestHelper.getPedidoEntity()));
        when(statusPedidoRepository.findByDescricao(anyString())).thenReturn(Optional.ofNullable(StatusPedidoEntity.builder().build()));
        pedidoRepositoryGateway.atualizarStatusPedido(123, "X");
    }

    @Test
    void testAtualizarStatusPedido() {
        pedidoRepositoryGateway.atualizarStatusPedido(123, 234);
    }

    @Test
    void listarPedidosPagos() {
        var result = pedidoRepositoryGateway.listarPedidosPagos();
    }

    @Test
    void buscarClientePorCPF() {
        var result = pedidoRepositoryGateway.buscarClientePorCPF("1234");
    }
}