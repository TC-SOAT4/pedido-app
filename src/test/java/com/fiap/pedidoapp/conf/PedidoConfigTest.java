package com.fiap.pedidoapp.conf;

import com.fiap.pedidoapp.application.pedido.usecases.RealizarCheckout;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class PedidoConfigTest {

    @Mock
    private RealizarCheckout realizarCheckout;

    @Test
    public void testRealizarCheckout() {

    }
}