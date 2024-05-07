package com.fiap.pedidoapp.application.produto.usecases;

import com.fiap.pedidoapp.application.produto.gateways.ProdutoGateway;
import com.fiap.pedidoapp.domain.produto.entity.Produto;
import com.fiap.pedidoapp.infrastructure.produto.controllers.dto.ProdutoResponse;

public class BuscarProdutoPorCodigo {

    private final ProdutoGateway produtoGateway;

    public BuscarProdutoPorCodigo(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    public ProdutoResponse buscarPorCodigo(Integer codigoProduto) {
        Produto produto = produtoGateway.buscarPorCodigo(codigoProduto);
        return new ProdutoResponse(produto);
    }

}
