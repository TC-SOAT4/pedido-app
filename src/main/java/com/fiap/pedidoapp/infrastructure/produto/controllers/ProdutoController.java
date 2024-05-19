package com.fiap.pedidoapp.infrastructure.produto.controllers;

import java.util.List;

import com.fiap.pedidoapp.application.produto.usecases.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.pedidoapp.infrastructure.produto.controllers.dto.CadastroProdutoRequest;
import com.fiap.pedidoapp.infrastructure.produto.controllers.dto.EditarProdutoRequest;
import com.fiap.pedidoapp.infrastructure.produto.controllers.dto.ProdutoResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Produtos")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final CriarProduto criarProduto;
    private final EditarProduto editarProduto;
    private final RemoverProduto removerProduto;
    private final ListarTodoProdutos listarTodoProdutos;
    private final BuscarProdutosPorCategoria buscarProdutosPorCategoria;
    private final BuscarProdutoPorCodigo buscarProdutoPorCodigo;

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por id", description = "buscar um produto por id")
    public ResponseEntity<ProdutoResponse> buscarPorId(@PathVariable(name = "id") Integer idProduto) {
        return ResponseEntity.ok().body(buscarProdutoPorCodigo.buscarPorCodigo(idProduto));
    }

    @GetMapping
    @Operation(summary = "Listar todos", description = "listar todos os produtos")
    public ResponseEntity<List<ProdutoResponse>> listarTodos() {
        return ResponseEntity.ok().body(listarTodoProdutos.listarTodos());
    }

    @PostMapping
    @Operation(summary = "Cadastrar", description = "Cadastrar um novo produto")
    public ResponseEntity<ProdutoResponse> cadastrarNovoProduto(
            @RequestBody @Valid CadastroProdutoRequest cadastroProdutoRequest) {
        return ResponseEntity.ok().body(criarProduto.cadastrar(cadastroProdutoRequest));
    }

    @PutMapping
    @Operation(summary = "Editar", description = "Editar os dados de um produto cadastrado")
    public ResponseEntity<ProdutoResponse> editarProduto(
            @RequestBody @Valid EditarProdutoRequest editarProdutoRequest) {
        return ResponseEntity.ok().body(editarProduto.editar(editarProdutoRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover", description = "Remover um produto")
    public ResponseEntity<ProdutoResponse> removerProduto(@PathVariable(name = "id") Integer idProduto) {
        removerProduto.remover(idProduto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/buscar-por-categoria")
    @Operation(summary = "Buscar por Categoria", description = "Buscar produtos por categoria")
    public ResponseEntity<List<ProdutoResponse>> buscarPorCategoria(
            @RequestParam(name = "codigoCategoria", required = true) Integer codigoCategoria) {
        return ResponseEntity.ok().body(buscarProdutosPorCategoria.buscarPorCategoria(codigoCategoria));
    }

}
