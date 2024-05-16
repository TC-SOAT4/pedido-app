package com.fiap.pedidoapp.domain.pedido.enums;

import java.util.Arrays;
import java.util.Optional;

import com.fiap.pedidoapp.exceptions.PedidoException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusPedidoEnum {

    RECEBIDO(1, "Recebido"),
    AGUARDANDO_PAGAMENTO(2, "Aguardando pagamento"),
    PAGO(3, "Pago"),
    EM_PREPARACAO(4, "Em preparação"),
    PRONTO(5, "Pronto"),
    FINALIZADO(6, "Finalizado");

    private Integer codigo;
    private String descricao;

    public static StatusPedidoEnum getFromDescricao(String descricao) {

        Optional<StatusPedidoEnum> statuOptional = Arrays.asList(StatusPedidoEnum.values()).stream()
                .filter(status -> status.getDescricao().equalsIgnoreCase(descricao))
                .findAny();

        if (statuOptional.isPresent())
            return statuOptional.get();

        throw new PedidoException("Status não encontrado");
    }

}
