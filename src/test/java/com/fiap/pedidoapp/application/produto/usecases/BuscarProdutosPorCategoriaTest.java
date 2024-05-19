package com.fiap.pedidoapp.application.produto.usecases;

import com.fiap.pedidoapp.domain.produto.entity.Categoria;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class BuscarProdutosPorCategoriaTest {

    @Mock
    private BuscarProdutosPorCategoria buscarProdutosPorCategoria;

    @Test
    void buscarPorCategoria() {
        buscarProdutosPorCategoria.buscarPorCategoria(123);
    }
}