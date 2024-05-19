package com.fiap.pedidoapp.bdd;

import com.fiap.pedidoapp.infrastructure.produto.controllers.dto.CadastroProdutoRequest;
import com.fiap.pedidoapp.infrastructure.produto.controllers.dto.ProdutoResponse;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class StepDefinition {
    private Response response;

    private ProdutoResponse produtoResponse;

    private final String ENDPOINT_API_PRODUTO = "http://localhost:8080/pedido-app/api/produtos";

    @Quando("registrar um novo produto")
    public ProdutoResponse registrar_um_novo_produto() {
        var produtoRequest = new CadastroProdutoRequest();
        produtoRequest.setNome("nome");
        produtoRequest.setDescricao("descricao");
        produtoRequest.setValor(BigDecimal.valueOf(100));
        produtoRequest.setCodigoCategoria(1);

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(produtoRequest)
                .when()
                .post(ENDPOINT_API_PRODUTO);

        return response.then().extract().as(ProdutoResponse.class);
    }

    @Entao("o produto e registrado com sucesso")
    public void o_produto_e_registrado_com_sucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }

    @Entao("deve ser apresentado")
    public void deve_ser_apresentado() {
        response.then()
                .body(matchesJsonSchemaInClasspath("./schemas/ProdutoResponseSchema.json"));
    }

    @Dado("que um produto ja foi cadastrado")
    public void que_um_produto_ja_foi_cadastrado() {
        produtoResponse = registrar_um_novo_produto();
    }
    @Quando("requisitar a busca do produto")
    public void requisitar_a_busca_do_produto() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(ENDPOINT_API_PRODUTO + "/{produtoId}", produtoResponse.getIdProduto().toString());
    }
    @Entao("o produto e exibido com sucesso")
    public void o_produto_e_exibido_com_sucesso() {
        response.then()
                .body(matchesJsonSchemaInClasspath("./schemas/ProdutoResponseSchema.json"));
    }

    @Quando("requisitar a edicao do produto")
    public void requisitar_a_edicao_do_produto() {
        produtoResponse.setDescricao("nova descricao");
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(produtoResponse)
                .when()
                .put(ENDPOINT_API_PRODUTO);
    }

    @Entao("o produto e editado com sucesso")
    public void o_produto_e_editado_com_sucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }

    @Quando("requisitar a remocao do produto")
    public void requisitar_a_remocao_do_produto() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete(ENDPOINT_API_PRODUTO + "/{id}", produtoResponse.getIdProduto().toString());
    }

    @Entao("o produto e removido com sucesso")
    public void o_produto_e_removido_com_sucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }

}
