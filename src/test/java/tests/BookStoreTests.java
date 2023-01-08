package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;



public class BookStoreTests {
    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://demoqa.com";
    }

    @Test
    void getBooksTest(){
        get("/BookStore/v1/Books")
                .then()
                .body("books", hasSize(greaterThan(0)));
    }

    @Test
    void getBooksWithAllTest(){
        given()
                .log().all()
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .log().all()
                .body("books", hasSize(greaterThan(0)));
    }

    @Test
    void getBooksWithSomeLogsTest(){
        given()
                .log().uri()
                .log().uri()
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .log().status()
                .log().body()
                .body("books", hasSize(greaterThan(0)));
    }

    @Test
    void generateTokenTest(){
        String data = "{ \"userName\": \"alex\", " +
                "\"password\": \"asdsad#frew_DFS2\" }";

        given()
                .contentType(JSON)
                .body(data)
                .log().uri()
                .log().body()
                .when()
                .post("/Account/v1/GenerateToken")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("shemas/generateToken_response_shema.json"))
                .body("status", is("Success"))
                .body("result", is("User authorized successfully."))
                .body("token.size()", greaterThan(10));

    }

//    @Test
//    void generateTokenWithAllureListenerTest(){
//        String data = "{ \"userName\": \"alex\", " +
//                "\"password\": \"asdsad#frew_DFS2\" }";
//
//        given()
//                .filter(new AllureRestAssured())
//                .contentType(JSON)
//                .body(data)
//                .log().uri()
//                .log().body()
//                .when()
//                .post("/Account/v1/GenerateToken")
//                .then()
//                .log().status()
//                .log().body()
//                .statusCode(200)
//                .body("status", is("Success"))
//                .body("result", is("User authorized successfully."))
//                .body("token.size()", greaterThan(10));
//
//    }
}
