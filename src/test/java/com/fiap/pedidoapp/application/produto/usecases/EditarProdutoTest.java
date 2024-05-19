package com.fiap.pedidoapp.application.produto.usecases;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fiap.pedidoapp.application.produto.gateways.ProdutoGateway;
import com.fiap.pedidoapp.domain.produto.entity.Categoria;
import com.fiap.pedidoapp.domain.produto.entity.Produto;
import com.fiap.pedidoapp.infrastructure.produto.controllers.dto.EditarProdutoRequest;

@ExtendWith(SpringExtension.class)
class EditarProdutoTest {
    @Mock
    private ProdutoGateway produtoGateway;

    @InjectMocks
    private EditarProduto editarProduto;

    @Test
    void editar() {
        EditarProdutoRequest editarProdutoRequest = new EditarProdutoRequest();
        editarProdutoRequest.setIdProduto(123);
        editarProdutoRequest.setNome("nomeProduto");
        editarProdutoRequest.setDescricao("descricaoProduto");
        editarProdutoRequest.setValor(BigDecimal.valueOf(100));
        editarProdutoRequest.setCategoria(Categoria.builder().build());
        editarProdutoRequest.setAtivo(true);

        Produto produto = Produto.builder()
                .idProduto(editarProdutoRequest.getIdProduto())
                .nome(editarProdutoRequest.getNome())
                .descricao(editarProdutoRequest.getDescricao())
                .valor(editarProdutoRequest.getValor())
                .categoria(editarProdutoRequest.getCategoria())
                .ativo(editarProdutoRequest.getAtivo())
                .build();

        when(produtoGateway.editar(any(Produto.class))).thenReturn(produto);

        var result = editarProduto.editar(editarProdutoRequest);
    }
}