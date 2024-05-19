package com.fiap.pedidoapp.infrastructure.produto.gateways;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fiap.pedidoapp.TestHelper;
import com.fiap.pedidoapp.infrastructure.produto.persistence.entity.ProdutoEntity;
import com.fiap.pedidoapp.infrastructure.produto.persistence.repository.ProdutoRepository;

@ExtendWith(SpringExtension.class)
class ProdutoRepositoryGatewayTest {
    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoRepositoryGateway produtoRepositoryGateway;

    @Test
    void testCadastrar() {
        when(produtoRepository.save(any(ProdutoEntity.class))).thenReturn(ProdutoEntity.builder().build());
        var result = produtoRepositoryGateway.cadastrar(TestHelper.getProduto());
        assertNotNull(result);

    }

    @Test
    void testEditar() {
        when(produtoRepository.save(any(ProdutoEntity.class))).thenReturn(ProdutoEntity.builder().build());
        var result = produtoRepositoryGateway.editar(TestHelper.getProduto());
        assertNotNull(result);
    }

    @Test
    void testRemover() {
        assertDoesNotThrow(() -> produtoRepositoryGateway.remover(123));
    }

    @Test
    void testListarTodos() {
        when(produtoRepository.findAll()).thenReturn(List.of(new ProdutoEntity(), new ProdutoEntity()));
        var lista = produtoRepositoryGateway.listarTodos();
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
    }

    @Test
    void testBuscarPorCategoria() {
        when(produtoRepository.findAllByCategoriaIdCategoria(any(Integer.class))).thenReturn(List.of(new ProdutoEntity(), new ProdutoEntity()));
        var lista = produtoRepositoryGateway.buscarPorCategoria(123);
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
    }

    @Test
    void testBuscarPorCodigo() {
        when(produtoRepository.findById(anyInt())).thenReturn(Optional.of(ProdutoEntity.builder().build()));
        var result = produtoRepositoryGateway.buscarPorCodigo(123);
                assertNotNull(result);

    }
}