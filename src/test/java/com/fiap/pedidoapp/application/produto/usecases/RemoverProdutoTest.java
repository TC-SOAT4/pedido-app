package com.fiap.pedidoapp.application.produto.usecases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fiap.pedidoapp.application.produto.gateways.ProdutoGateway;

@ExtendWith(SpringExtension.class)
public class RemoverProdutoTest {

    @Mock
    private ProdutoGateway produtoGateway;

    @InjectMocks
    private RemoverProduto removerProduto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCallRemoveProduct() {
        Integer idProduto = 1;
        removerProduto.remover(idProduto);
    }
}
