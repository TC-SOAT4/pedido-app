package com.fiap.pedidoapp.domain.produto.entity;

import java.math.BigDecimal;

import com.fiap.pedidoapp.infrastructure.produto.persistence.entity.ProdutoEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Produto {

    private Integer idProduto;
    private String nome;
    private String descricao;
    private BigDecimal valor;
    private Boolean ativo;

    private Categoria categoria;

    public Produto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Produto(ProdutoEntity produtoEntity) {
        this.idProduto = produtoEntity.getIdProduto();
        this.nome = produtoEntity.getNome();
        this.descricao = produtoEntity.getDescricao();
        this.valor = produtoEntity.getValor();
        this.ativo = produtoEntity.getAtivo();
        this.categoria = produtoEntity.getCategoria() != null ? new Categoria(produtoEntity.getCategoria()) : null;
    }

}
