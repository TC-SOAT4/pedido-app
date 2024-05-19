package com.fiap.pedidoapp.infrastructure.pedido.controllers.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ItemDTO {

    private Integer codigoProduto;
    private Integer quantidade;
    
}
