package com.fiap.pedidoapp.infrastructure.pedido.controllers.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NovoPedidoDTO {

    @NotNull
    @Size(min = 1)
    private List<ItemDTO> itens;

}
