package com.fiap.pedidoapp.application.produto.usecases;

import com.fiap.pedidoapp.application.produto.gateways.ProdutoGateway;
import com.fiap.pedidoapp.domain.produto.entity.Categoria;
import com.fiap.pedidoapp.domain.produto.entity.Produto;
import com.fiap.pedidoapp.infrastructure.produto.controllers.dto.CadastroProdutoRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CriarProdutoTest {
    @Mock
    private ProdutoGateway produtoGateway;

    @InjectMocks
    private CriarProduto criarProduto;

    @Test
    void cadastrar() {
        CadastroProdutoRequest cadastroProdutoRequest = new CadastroProdutoRequest();
        cadastroProdutoRequest.setNome("nome");
        cadastroProdutoRequest.setDescricao("descricao");
        cadastroProdutoRequest.setValor(BigDecimal.valueOf(100));
        cadastroProdutoRequest.setCodigoCategoria(123);

        Produto novoProduto = Produto.builder()
                .nome(cadastroProdutoRequest.getNome())
                .descricao(cadastroProdutoRequest.getDescricao())
                .valor(cadastroProdutoRequest.getValor())
                .categoria(new Categoria(cadastroProdutoRequest.getCodigoCategoria()))
                .ativo(Boolean.TRUE)
                .build();

        when(produtoGateway.cadastrar(novoProduto)).thenReturn(novoProduto);

        var result = criarProduto.cadastrar(cadastroProdutoRequest);
    }
}