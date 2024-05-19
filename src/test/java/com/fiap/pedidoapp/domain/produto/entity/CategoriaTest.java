package com.fiap.pedidoapp.domain.produto.entity;

import com.fiap.pedidoapp.infrastructure.produto.persistence.entity.CategoriaEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class CategoriaTest {

    @Test
    void testCategoria() {
        
        var categoria1 = new Categoria(
                CategoriaEntity.builder()
                        .idCategoria(1)
                        .nome("nome")
                        .ativo(true)
                        .build());
        assertNotNull(categoria1);
        assertNotNull(categoria1.getIdCategoria());
        assertNotNull(categoria1.getNome());
        assertNotNull(categoria1.getAtivo());
    }

}