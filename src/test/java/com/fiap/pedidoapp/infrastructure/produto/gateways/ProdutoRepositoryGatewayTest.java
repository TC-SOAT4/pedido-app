package com.fiap.pedidoapp.infrastructure.produto.gateways;

import com.fiap.pedidoapp.TestHelper;
import com.fiap.pedidoapp.domain.produto.entity.Produto;
import com.fiap.pedidoapp.infrastructure.produto.persistence.entity.ProdutoEntity;
import com.fiap.pedidoapp.infrastructure.produto.persistence.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.*;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ProdutoRepositoryGatewayTest {
    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoRepositoryGateway produtoRepositoryGateway;

    @Test
    void cadastrar() {
        when(produtoRepository.save(any(ProdutoEntity.class))).thenReturn(ProdutoEntity.builder().build());
        produtoRepositoryGateway.cadastrar(TestHelper.getProduto());
    }

    @Test
    void editar() {
        when(produtoRepository.save(any(ProdutoEntity.class))).thenReturn(ProdutoEntity.builder().build());
        var result = produtoRepositoryGateway.editar(TestHelper.getProduto());
    }

    @Test
    void remover() {
        produtoRepositoryGateway.remover(123);
    }

    @Test
    void listarTodos() {
        produtoRepositoryGateway.listarTodos();
    }

    @Test
    void buscarPorCategoria() {
        produtoRepositoryGateway.buscarPorCategoria(123);
    }

    @Test
    void buscarPorCodigo() {
        when(produtoRepository.findById(anyInt())).thenReturn(Optional.of(ProdutoEntity.builder().build()));
        var result = produtoRepositoryGateway.buscarPorCodigo(123);
    }
}