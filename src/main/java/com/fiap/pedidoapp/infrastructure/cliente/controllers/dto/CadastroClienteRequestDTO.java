package com.fiap.pedidoapp.infrastructure.cliente.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CadastroClienteRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
    @Size(min=11)
    private String cpf;
    
}
