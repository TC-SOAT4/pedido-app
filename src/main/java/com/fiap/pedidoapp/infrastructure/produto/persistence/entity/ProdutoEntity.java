package com.fiap.pedidoapp.infrastructure.produto.persistence.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "Produto")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduto;
    private String nome;
    private String descricao;
    private BigDecimal valor;
    private Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "categoriaId")
    private CategoriaEntity categoria;

    public ProdutoEntity(Integer idProduto) {
        this.idProduto = idProduto;
    }

}
