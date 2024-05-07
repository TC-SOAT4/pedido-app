package com.fiap.pedidoapp.application.produto.usecases;

import com.fiap.pedidoapp.application.produto.gateways.ProdutoGateway;
import com.fiap.pedidoapp.domain.produto.entity.Categoria;
import com.fiap.pedidoapp.domain.produto.entity.Produto;
import com.fiap.pedidoapp.infrastructure.produto.controllers.dto.CadastroProdutoRequest;
import com.fiap.pedidoapp.infrastructure.produto.controllers.dto.ProdutoResponse;

public class CriarProduto {

    private final ProdutoGateway produtoGateway;

    public CriarProduto(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    public ProdutoResponse cadastrar(CadastroProdutoRequest cadastroProdutoRequest) {
        Produto novoProduto = Produto.builder()
                .nome(cadastroProdutoRequest.getNome())
                .descricao(cadastroProdutoRequest.getDescricao())
                .valor(cadastroProdutoRequest.getValor())
                .categoria(new Categoria(cadastroProdutoRequest.getCodigoCategoria()))
                .ativo(Boolean.TRUE)
                .build();

        novoProduto = produtoGateway.cadastrar(novoProduto);
        return new ProdutoResponse(novoProduto);
    }

}
