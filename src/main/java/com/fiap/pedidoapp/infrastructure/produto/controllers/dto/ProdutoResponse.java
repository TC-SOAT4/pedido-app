package com.fiap.pedidoapp.infrastructure.produto.controllers.dto;

import java.math.BigDecimal;

import com.fiap.pedidoapp.domain.produto.entity.Categoria;
import com.fiap.pedidoapp.domain.produto.entity.Produto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponse {

    private Integer idProduto;
    private String nome;
    private String descricao;
    private BigDecimal valor;
    private Boolean ativo;

    private Categoria categoria;


    public ProdutoResponse(Produto produto){
        this.idProduto = produto.getIdProduto();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.valor = produto.getValor();
        this.ativo = produto.getAtivo();
        this.categoria = produto.getCategoria();
    }

}
