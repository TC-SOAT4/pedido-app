package com.fiap.pedidoapp.application.produto.usecases;

import java.util.List;

import com.fiap.pedidoapp.application.produto.gateways.ProdutoGateway;
import com.fiap.pedidoapp.infrastructure.produto.controllers.dto.ProdutoResponse;

public class ListarTodoProdutos {

    private final ProdutoGateway produtoGateway;

    public ListarTodoProdutos(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    public List<ProdutoResponse> listarTodos() {
        return produtoGateway.listarTodos().stream().map(ProdutoResponse::new).toList();
    }
}
