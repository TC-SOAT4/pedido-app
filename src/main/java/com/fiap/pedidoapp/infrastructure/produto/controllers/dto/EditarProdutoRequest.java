package com.fiap.pedidoapp.infrastructure.produto.controllers.dto;

import java.math.BigDecimal;

import com.fiap.pedidoapp.domain.produto.entity.Categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditarProdutoRequest {

    @NotNull
    private Integer idProduto;

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private Categoria categoria;

    @NotNull
    private Boolean ativo;
    
}
