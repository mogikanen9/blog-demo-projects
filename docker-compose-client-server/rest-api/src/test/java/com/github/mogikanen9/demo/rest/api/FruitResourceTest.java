package com.github.mogikanen9.demo.rest.api;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class FruitResourceTest {

    @Test
    public void testFruitsEndpoint() {
        given()
          .when().get("/fruits")
          .then()
             .statusCode(200)
             .body(containsString("Tropical fruit"));
    }

}