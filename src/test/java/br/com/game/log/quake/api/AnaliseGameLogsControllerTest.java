package br.com.game.log.quake.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.io.File;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.text.IsEmptyString.emptyOrNullString;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnaliseGameLogsControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/upload";
    }

    @Test
    public void deveRetornarStatus200() {
        var file = new File("src/main/resources/file/games.log");
        given()
                .accept("application/json")
                    .multiPart("file", file)
                .when()
                    .post()
                .then()
                    .statusCode(HttpStatus.OK.value())
                    .body(not(is(emptyOrNullString())));

    }

    @Test
    public void deveRetornarStatus400() {
        var file = new File("src/main/resources/file/games_formato_invalido.png");
        given()
                .accept("application/json")
                .multiPart("file", file)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());

    }
}
