package com.fiap.pedidoapp.application.produto.usecases;

import com.fiap.pedidoapp.application.produto.gateways.ProdutoGateway;
import com.fiap.pedidoapp.domain.produto.entity.Produto;
import com.fiap.pedidoapp.infrastructure.produto.controllers.dto.EditarProdutoRequest;
import com.fiap.pedidoapp.infrastructure.produto.controllers.dto.ProdutoResponse;

public class EditarProduto {

    private final ProdutoGateway produtoGateway;

    public EditarProduto(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    public ProdutoResponse editar(EditarProdutoRequest editarProdutoRequest) {
        Produto produto = Produto.builder()
                .idProduto(editarProdutoRequest.getIdProduto())
                .nome(editarProdutoRequest.getNome())
                .descricao(editarProdutoRequest.getDescricao())
                .valor(editarProdutoRequest.getValor())
                .categoria(editarProdutoRequest.getCategoria())
                .ativo(editarProdutoRequest.getAtivo())
                .build();

        produto = produtoGateway.editar(produto);
        return new ProdutoResponse(produto);
    }

}
