package com.fiap.pedidoapp.infrastructure.produto.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.pedidoapp.infrastructure.produto.persistence.entity.ProdutoEntity;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Integer> {

    public List<ProdutoEntity> findAllByCategoriaIdCategoria(Integer idCategoria);

}
