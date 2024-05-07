package com.fiap.pedidoapp.infrastructure.cliente.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.pedidoapp.application.cliente.usecases.BuscarClientePorCpf;
import com.fiap.pedidoapp.application.cliente.usecases.CadastrarCliente;
import com.fiap.pedidoapp.infrastructure.cliente.controllers.dto.CadastroClienteRequestDTO;
import com.fiap.pedidoapp.infrastructure.cliente.controllers.dto.ClienteResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Clientes")
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final CadastrarCliente cadastrarCliente;

    private final BuscarClientePorCpf buscarClientePorCpf;

    public ClienteController(CadastrarCliente cadastrarCliente, BuscarClientePorCpf buscarClientePorCpf) {
        this.cadastrarCliente = cadastrarCliente;
        this.buscarClientePorCpf = buscarClientePorCpf;
    }

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

}
