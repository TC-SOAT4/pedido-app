package com.fiap.pedidoapp.application.produto.usecases;

import com.fiap.pedidoapp.application.produto.gateways.ProdutoGateway;
import com.fiap.pedidoapp.domain.produto.entity.Produto;
import com.fiap.pedidoapp.infrastructure.produto.controllers.dto.ProdutoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BuscarProdutosPorCategoriaTest {

    @Mock
    private ProdutoGateway produtoGateway;

    @InjectMocks
    private BuscarProdutosPorCategoria buscarProdutosPorCategoria;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByCategoria() {
        buscarProdutosPorCategoria.buscarPorCategoria(123);
    }

    @Test
    void shouldFindByCategoriaWithNotEmpty() {
        Integer idCategoria = 10;
        Produto produto1 = new Produto();
        produto1.setIdProduto(1);
        produto1.setNome("Produto 1");
        produto1.setValor(new BigDecimal(100));

        Produto produto2 = new Produto();
        produto2.setIdProduto(2);
        produto2.setNome("Produto 2");
        produto2.setValor(new BigDecimal(100));

        List<Produto> produtos = Arrays.asList(produto1, produto2);
        when(produtoGateway.buscarPorCategoria(idCategoria)).thenReturn(produtos);

        List<ProdutoResponse> resultado = buscarProdutosPorCategoria.buscarPorCategoria(idCategoria);
    }

    @Test
    void shouldFindByCategoriaWithEmpty() {
        Integer idCategoria = 99;
        when(produtoGateway.buscarPorCategoria(idCategoria)).thenReturn(List.of());
        List<ProdutoResponse> resultado = buscarProdutosPorCategoria.buscarPorCategoria(idCategoria);

    }
}