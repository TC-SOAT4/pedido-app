package com.fiap.pedidoapp.infrastructure.pedido.controllers;

import com.fiap.pedidoapp.TestHelper;
import com.fiap.pedidoapp.application.pedido.usecases.RealizarCheckout;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.security.Principal;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
class PedidoPrivateControllerTest {
    @Mock
    private RealizarCheckout realizarCheckout;

    @InjectMocks
    private PedidoPrivateController controller;

    @Test
    public void criarNovoPedido() {
        Principal mockPrincipal = mock(Principal.class);
        var result = controller.criarNovoPedido(TestHelper.getNovoPedidoDTO(), mockPrincipal);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}