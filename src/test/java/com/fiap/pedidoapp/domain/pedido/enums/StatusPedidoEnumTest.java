package com.fiap.pedidoapp.domain.pedido.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.fiap.pedidoapp.exceptions.PedidoException;

class StatusPedidoEnumTest {

    @Test
    void testGetCodigo() {
        assertEquals(1, StatusPedidoEnum.RECEBIDO.getCodigo());
        assertEquals(2, StatusPedidoEnum.AGUARDANDO_PAGAMENTO.getCodigo());
        assertEquals(3, StatusPedidoEnum.PAGO.getCodigo());
        assertEquals(4, StatusPedidoEnum.EM_PREPARACAO.getCodigo());
        assertEquals(5, StatusPedidoEnum.PRONTO.getCodigo());
        assertEquals(6, StatusPedidoEnum.FINALIZADO.getCodigo());
    }

    @Test
    void testGetDescricao() {
        assertEquals("Recebido", StatusPedidoEnum.RECEBIDO.getDescricao());
        assertEquals("Aguardando pagamento", StatusPedidoEnum.AGUARDANDO_PAGAMENTO.getDescricao());
        assertEquals("Pago", StatusPedidoEnum.PAGO.getDescricao());
        assertEquals("Em preparação", StatusPedidoEnum.EM_PREPARACAO.getDescricao());
        assertEquals("Pronto", StatusPedidoEnum.PRONTO.getDescricao());
        assertEquals("Finalizado", StatusPedidoEnum.FINALIZADO.getDescricao());
    }

    @Test
    void testGetFromDescricao() {
        assertEquals(StatusPedidoEnum.RECEBIDO, StatusPedidoEnum.getFromDescricao("Recebido"));
        assertEquals(StatusPedidoEnum.RECEBIDO, StatusPedidoEnum.getFromDescricao("recebido"));
        assertEquals(StatusPedidoEnum.AGUARDANDO_PAGAMENTO, StatusPedidoEnum.getFromDescricao("Aguardando pagamento"));
        assertEquals(StatusPedidoEnum.AGUARDANDO_PAGAMENTO, StatusPedidoEnum.getFromDescricao("AGUARDANDO PAGAMENTO"));
        assertEquals(StatusPedidoEnum.PAGO, StatusPedidoEnum.getFromDescricao("Pago"));
        assertEquals(StatusPedidoEnum.PAGO, StatusPedidoEnum.getFromDescricao("PaGo"));
        assertEquals(StatusPedidoEnum.EM_PREPARACAO, StatusPedidoEnum.getFromDescricao("Em preparação"));
        assertEquals(StatusPedidoEnum.EM_PREPARACAO, StatusPedidoEnum.getFromDescricao("em PRePARAÇÃO"));
        assertEquals(StatusPedidoEnum.PRONTO, StatusPedidoEnum.getFromDescricao("Pronto"));
        assertEquals(StatusPedidoEnum.PRONTO, StatusPedidoEnum.getFromDescricao("pronto"));
        assertEquals(StatusPedidoEnum.FINALIZADO, StatusPedidoEnum.getFromDescricao("Finalizado"));
        assertEquals(StatusPedidoEnum.FINALIZADO, StatusPedidoEnum.getFromDescricao("FINALIZADO"));
    }

    @Test
    void testGetFromDescricaoInvalida() {
        Exception exception = assertThrows(PedidoException.class, () -> {
            StatusPedidoEnum.getFromDescricao("Não existe");
        });
        assertEquals("Status não encontrado", exception.getMessage());
    }
}
