package br.com.fiap.steps;

import io.cucumber.java.pt.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiSteps {

    private Response response;
    private static final String BASE_URL = System.getProperty("api.url", "http://localhost:8080");

    @Dado("que a API do CarbonTrack esta disponivel")
    public void apiDisponivel() {
        RestAssured.baseURI = BASE_URL;
    }

    @Dado("que existe um residuo cadastrado com ID {int}")
    public void existeResiduoComId(int id) {
        RestAssured.baseURI = BASE_URL;
        // Garante que o dado existe (pre-populado pelo migration V1)
    }

    @Dado("que existe um ponto de coleta cadastrado com ID {int}")
    public void existePontoColetaComId(int id) {
        RestAssured.baseURI = BASE_URL;
    }

    @Quando("eu faco uma requisicao GET para {string}")
    public void fazRequisicaoGet(String endpoint) {
        response = given()
                .header("Content-Type", "application/json")
                .when()
                .get(endpoint);
    }

    @Quando("eu faco uma requisicao POST para {string} com o corpo:")
    public void fazRequisicaoPost(String endpoint, String body) {
        response = given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(endpoint);
    }

    @Entao("o status code da resposta deve ser {int}")
    public void verificaStatusCode(int statusCode) {
        Assertions.assertEquals(statusCode, response.getStatusCode(),
                "Status code esperado: " + statusCode + ", recebido: " + response.getStatusCode()
                + "\nBody: " + response.getBody().asString());
    }

    @E("a resposta deve ser uma lista")
    public void respostaDeveSerLista() {
        Assertions.assertTrue(
                response.getBody().asString().trim().startsWith("["),
                "Esperado uma lista JSON, mas recebeu: " + response.getBody().asString()
        );
    }

    @E("a resposta deve conter o campo {string}")
    public void respostaContemCampo(String campo) {
        String body = response.getBody().asString();
        Assertions.assertTrue(body.contains("\"" + campo + "\""),
                "Campo '" + campo + "' nao encontrado no corpo: " + body);
    }

    @E("a resposta deve conter o campo {string} com valor {string}")
    public void respostaContemCampoComValor(String campo, String valor) {
        String body = response.getBody().asString();
        boolean contemString = body.contains("\"" + campo + "\":\"" + valor + "\"");
        boolean contemNumero = body.contains("\"" + campo + "\":" + valor);
        Assertions.assertTrue(contemString || contemNumero,
                "Campo '" + campo + "' com valor '" + valor + "' nao encontrado em: " + body);
    }
}
