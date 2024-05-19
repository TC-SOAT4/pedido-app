package com.fiap.pedidoapp.infrastructure.pedido.controllers;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.pedidoapp.application.pedido.usecases.RealizarCheckout;
import com.fiap.pedidoapp.infrastructure.pedido.controllers.dto.NovoPedidoDTO;
import com.fiap.pedidoapp.infrastructure.pedido.controllers.dto.ResumoPedidoDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Pedidos")
@RequiredArgsConstructor
@RestController
@RequestMapping("/private/api/pedidos")
public class PedidoPrivateController {

    private final RealizarCheckout realizarCheckout;

    @PostMapping("/checkout")
    @Operation(summary = "Realizar Checkout de um clinete autenticado", description = "Enviar pedido para fila", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<ResumoPedidoDTO> criarNovoPedido(
            @RequestBody @Valid NovoPedidoDTO novoPedidoDTO, Principal principal) {
               
                String cpf = principal.getName();
        return ResponseEntity.ok().body(realizarCheckout.checkout(novoPedidoDTO, cpf));
    }

   
    
}

 