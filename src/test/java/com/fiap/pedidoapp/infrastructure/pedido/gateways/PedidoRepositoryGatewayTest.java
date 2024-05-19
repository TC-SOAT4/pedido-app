package com.fiap.pedidoapp.infrastructure.pedido.gateways;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fiap.pedidoapp.TestHelper;
import com.fiap.pedidoapp.application.cliente.usecases.BuscarClientePorCpf;
import com.fiap.pedidoapp.application.produto.usecases.BuscarProdutoPorCodigo;
import com.fiap.pedidoapp.domain.pedido.enums.StatusPedidoEnum;
import com.fiap.pedidoapp.infrastructure.pedido.persistence.entity.PedidoEntity;
import com.fiap.pedidoapp.infrastructure.pedido.persistence.entity.StatusPedidoEntity;
import com.fiap.pedidoapp.infrastructure.pedido.persistence.repository.PedidoRepository;
import com.fiap.pedidoapp.infrastructure.pedido.persistence.repository.StatusPedidoRepository;

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
    void listarPedidosListaVazia() {
        var result = pedidoRepositoryGateway.listarPedidos();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void checkout() {
        when(buscarProdutoPorCodigo.buscarPorCodigo(anyInt())).thenReturn(TestHelper.getProdutoResponse());
        when(pedidoRepository.saveAndFlush(any(PedidoEntity.class))).thenReturn(TestHelper.getPedidoEntity());
        var result = pedidoRepositoryGateway.checkout(TestHelper.getPedido());
        assertNotNull(result);
        assertEquals(TestHelper.getPedidoEntity().getValorTotal(), result.getValorTotal());
    }

    @Test
    void buscarPorId() {
        when(pedidoRepository.findById(anyInt())).thenReturn(Optional.ofNullable(TestHelper.getPedidoEntity()));
        var result = pedidoRepositoryGateway.buscarPorId(123);
        assertNotNull(result);
        assertEquals(TestHelper.getPedidoEntity().getIdPedido(), result.getIdPedido());
    }

    @Test
    void atualizarStatusPedido() {
        when(pedidoRepository.findById(anyInt())).thenReturn(Optional.ofNullable(TestHelper.getPedidoEntity()));
        when(statusPedidoRepository.findByDescricao(anyString())).thenReturn(Optional.ofNullable(StatusPedidoEntity.builder().build()));
        pedidoRepositoryGateway.atualizarStatusPedido(123, "X");
        verify(statusPedidoRepository, atLeastOnce()).findByDescricao(any(String.class));
    }

    @Test
    void testAtualizarStatusPedido() {
        doNothing().when(pedidoRepository).updateStatusByCdPedido(any(Integer.class), any(StatusPedidoEntity.class));
        pedidoRepositoryGateway.atualizarStatusPedido(123, 234);
        verify(pedidoRepository, atLeastOnce()).updateStatusByCdPedido(any(Integer.class), any(StatusPedidoEntity.class));
    }

    @Test
    void listarPedidosPagos() {
        var result = pedidoRepositoryGateway.listarPedidosPagos();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void buscarClientePorCPFClienteNaoCadastrado() {
        var result = pedidoRepositoryGateway.buscarClientePorCPF("1234");
        assertNull(result);
    }
}