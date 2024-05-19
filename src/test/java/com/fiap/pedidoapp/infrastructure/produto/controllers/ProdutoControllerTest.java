package com.fiap.pedidoapp.infrastructure.produto.controllers;

import com.fiap.pedidoapp.application.produto.usecases.*;
import com.fiap.pedidoapp.infrastructure.produto.controllers.dto.CadastroProdutoRequest;
import com.fiap.pedidoapp.infrastructure.produto.controllers.dto.EditarProdutoRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class ProdutoControllerTest {
    @Mock
    private BuscarProdutosPorCategoria buscarProdutosPorCategoria;

    @Mock
    private RemoverProduto removerProduto;

    @Mock
    private EditarProduto editarProduto;

    @Mock
    private CriarProduto criarProduto;

    @Mock
    private ListarTodoProdutos listarTodoProdutos;

    @InjectMocks
    private ProdutoController produtoController;

    @Test
    void listarTodos() {
        var result = produtoController.listarTodos();
    }

    @Test
    void cadastrarNovoProduto() {
        CadastroProdutoRequest cadastroProdutoRequest = new CadastroProdutoRequest();
        var result = produtoController.cadastrarNovoProduto(cadastroProdutoRequest);
    }

    @Test
    void editarProduto() {
        EditarProdutoRequest editarProdutoRequest = new EditarProdutoRequest();
        var result = produtoController.editarProduto(editarProdutoRequest);
    }

    @Test
    void removerProduto() {
        var result = produtoController.removerProduto(123);
    }

    @Test
    void buscarPorCategoria() {
        var result = produtoController.buscarPorCategoria(1);
    }
}