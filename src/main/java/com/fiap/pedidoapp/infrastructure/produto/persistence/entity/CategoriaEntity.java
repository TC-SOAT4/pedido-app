package com.fiap.pedidoapp.infrastructure.produto.persistence.entity;

import com.fiap.pedidoapp.domain.produto.entity.Categoria;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Categoria")
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoria;
    
    private String nome;
    private Boolean ativo;

    public CategoriaEntity(Integer idCategoria){
        this.idCategoria =  idCategoria;
    }

    public CategoriaEntity(Categoria categoria) {
        this.idCategoria = categoria.getIdCategoria();
        this.nome = categoria.getNome();
        this.ativo = categoria.getAtivo();
    }

}
