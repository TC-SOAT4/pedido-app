package com.fiap.pedidoapp.application.produto.usecases;

import com.fiap.pedidoapp.application.produto.gateways.ProdutoGateway;

public class RemoverProduto {

    private final ProdutoGateway produtoGateway;

    public RemoverProduto(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    public void remover(Integer id) {
        produtoGateway.remover(id);
    }
}
