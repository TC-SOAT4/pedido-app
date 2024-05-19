package com.fiap.pedidoapp.application.produto.usecases;

import com.fiap.pedidoapp.TestHelper;
import com.fiap.pedidoapp.application.produto.gateways.ProdutoGateway;
import org.hibernate.annotations.ListIndexJdbcTypeCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BuscarProdutoPorCodigoTest {
    @Mock
    private ProdutoGateway produtoGateway;

    @InjectMocks
    private BuscarProdutoPorCodigo buscarProdutoPorCodigo;

    @Test
    void buscarPorCodigo() {
        when(produtoGateway.buscarPorCodigo(anyInt())).thenReturn(TestHelper.getProduto());
        var result = buscarProdutoPorCodigo.buscarPorCodigo(123);
    }
}