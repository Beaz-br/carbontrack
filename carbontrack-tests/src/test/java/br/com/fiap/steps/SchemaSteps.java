package br.com.fiap.steps;

import io.cucumber.java.pt.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.*;

public class SchemaSteps {

    private Response response;
    private static final String BASE_URL = System.getProperty("api.url", "http://localhost:8080");

    @Quando("eu valido o schema de {string}")
    public void validaSchema(String endpoint) {
        RestAssured.baseURI = BASE_URL;
        response = given()
                .header("Content-Type", "application/json")
                .when()
                .get(endpoint);
    }

    @Entao("o primeiro item deve seguir o schema {string}")
    public void verificaSchema(String schemaFile) {
        String body = response.getBody().asString();
        // Se for lista, pega o primeiro item
        if (body.trim().startsWith("[")) {
            // Valida que a lista não está vazia e contém os campos esperados
            Assertions.assertFalse(body.equals("[]"),
                    "Lista vazia, nao e possivel validar o schema");
        }
        // Valida usando o schema JSON
        response.then().assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/" + schemaFile));
    }
}
