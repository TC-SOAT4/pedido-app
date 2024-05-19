package com.fiap.pedidoapp.domain.produto.entity;

import com.fiap.pedidoapp.infrastructure.produto.persistence.entity.CategoriaEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Categoria {

    private Integer idCategoria;

    private String nome;
    private Boolean ativo;

    public Categoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Categoria(CategoriaEntity categoriaEntity) {
        this.idCategoria = categoriaEntity.getIdCategoria();
        this.nome = categoriaEntity.getNome();
        this.ativo = categoriaEntity.getAtivo();
    }

}
