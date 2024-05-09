package com.fiap.pedidoapp.infrastructure.pedido.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.pedidoapp.application.pedido.usecases.AtualizarStatusPedido;
import com.fiap.pedidoapp.application.pedido.usecases.BuscarPedidoPorId;
import com.fiap.pedidoapp.application.pedido.usecases.ListarPedidos;
import com.fiap.pedidoapp.application.pedido.usecases.RealizarCheckout;
import com.fiap.pedidoapp.infrastructure.pedido.controllers.dto.NovoPedidoDTO;
import com.fiap.pedidoapp.infrastructure.pedido.controllers.dto.PedidoListaDTO;
import com.fiap.pedidoapp.infrastructure.pedido.controllers.dto.ResumoPedidoDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Pedidos")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final RealizarCheckout realizarCheckout;

    private final ListarPedidos listarPedidos;

    private final BuscarPedidoPorId buscarPedidoPorId;

    private final AtualizarStatusPedido atualizarStatusPedido;

    @PostMapping("/checkout")
    @Operation(summary = "Realizar Checkout", description = "Enviar pedido para fila")
    public ResponseEntity<ResumoPedidoDTO> criarNovoPedido(
            @RequestBody @Valid NovoPedidoDTO novoPedidoDTO) {
        return ResponseEntity.ok().body(realizarCheckout.checkout(novoPedidoDTO));
    }

    @GetMapping
    @Operation(summary = "Listar", description = "Lista pedidos")
    public ResponseEntity<List<PedidoListaDTO>> listarPedidos() {
        return ResponseEntity.ok().body(listarPedidos.listarPedidos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar", description = "Buscar")
    public ResponseEntity<ResumoPedidoDTO> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok().body(buscarPedidoPorId.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar status", description = "Atualizar status pedido")
    public ResponseEntity<Void> atualizarStatusPedido(@PathVariable Integer id, @RequestParam String novoStatus) {
        atualizarStatusPedido.atualizarStatusPedido(id, novoStatus);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
