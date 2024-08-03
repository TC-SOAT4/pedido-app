package com.fiap.pedidoapp.infrastructure.cliente.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fiap.pedidoapp.application.cliente.usecases.BuscarClientePorCpf;
import com.fiap.pedidoapp.application.cliente.usecases.CadastrarCliente;
import com.fiap.pedidoapp.application.cliente.usecases.ExcluirCliente;
import com.fiap.pedidoapp.application.cliente.usecases.InativarCliente;
import com.fiap.pedidoapp.infrastructure.cliente.controllers.dto.CadastroClienteRequestDTO;
import com.fiap.pedidoapp.infrastructure.cliente.controllers.dto.ClienteResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Clientes")
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private CadastrarCliente cadastrarCliente;

    @Autowired
    private BuscarClientePorCpf buscarClientePorCpf;

    @Autowired
    private InativarCliente inativarCliente;

    @Autowired
    private ExcluirCliente excluirCliente;

    @PostMapping
    @Operation(summary = "Cadastrar", description = "Cadastrar um novo cliente")
    public ResponseEntity<ClienteResponseDTO> cadastrarNovoCliente(
            @RequestBody @Valid CadastroClienteRequestDTO cadastroClienteRequestDto) {
        return ResponseEntity.ok().body(cadastrarCliente.cadatrarCliente(cadastroClienteRequestDto));
    }

    @GetMapping("/buscar-por-cpf")
    @Operation(summary = "Buscar por CPF", description = "Buscar um cliente utilizando o CPF")
    public ResponseEntity<ClienteResponseDTO> buscarPorCpf(@RequestParam(name = "cpf", required = true) String cpf) {
        return ResponseEntity.ok().body(buscarClientePorCpf.buscarPorCpf(cpf));
    }

    @PutMapping("/{id}/inativar")
    @Operation(summary = "Inativar Cliente", description = "Inativar um cliente pelo ID")
    public ResponseEntity<Void> inativarCliente(@PathVariable Integer id) {
        inativarCliente.execute(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir Cliente", description = "Excluir um cliente pelo ID")
    public ResponseEntity<Void> excluirCliente(@PathVariable Integer id) {
        excluirCliente.execute(id);
        return ResponseEntity.noContent().build();
    }
}
