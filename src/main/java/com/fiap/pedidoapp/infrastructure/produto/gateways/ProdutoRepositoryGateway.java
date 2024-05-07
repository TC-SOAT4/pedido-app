package com.fiap.pedidoapp.infrastructure.produto.gateways;

import java.util.List;

import com.fiap.pedidoapp.application.produto.gateways.ProdutoGateway;
import com.fiap.pedidoapp.domain.produto.entity.Produto;
import com.fiap.pedidoapp.infrastructure.produto.persistence.entity.CategoriaEntity;
import com.fiap.pedidoapp.infrastructure.produto.persistence.entity.ProdutoEntity;
import com.fiap.pedidoapp.infrastructure.produto.persistence.repository.ProdutoRepository;

public class ProdutoRepositoryGateway implements ProdutoGateway {

    private final ProdutoRepository produtoRepository;

    public ProdutoRepositoryGateway(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto cadastrar(Produto produto) {

        ProdutoEntity novoProduto = ProdutoEntity.builder()
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .valor(produto.getValor())
                .categoria(new CategoriaEntity(produto.getCategoria()))
                .ativo(Boolean.TRUE)
                .build();

        novoProduto = produtoRepository.save(novoProduto);

        return new Produto(novoProduto);
    }

    @Override
    public Produto editar(Produto produto) {
        ProdutoEntity novoProduto = ProdutoEntity.builder()
                .idProduto(produto.getIdProduto())
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .valor(produto.getValor())
                .categoria(new CategoriaEntity(produto.getCategoria()))
                .ativo(produto.getAtivo())
                .build();

        novoProduto = produtoRepository.save(novoProduto);

        return new Produto(novoProduto);
    }

    @Override
    public void remover(Integer id) {
        produtoRepository.findById(id).ifPresent(produtoRepository::delete);
    }

    @Override
    public List<Produto> listarTodos() {
        return produtoRepository.findAll().stream().map(Produto::new).toList();
    }

    @Override
    public List<Produto> buscarPorCategoria(Integer idCategoria) {
        return produtoRepository.findAllByCategoriaIdCategoria(idCategoria).stream().map(Produto::new).toList();
    }

    @Override
    public Produto buscarPorCodigo(Integer codigoProduto) {
        ProdutoEntity produto = produtoRepository.findById(codigoProduto)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return new Produto(produto);
    }

}
