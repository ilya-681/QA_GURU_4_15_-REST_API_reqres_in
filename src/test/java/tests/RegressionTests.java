package tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;


public class RegressionTests extends TestBase {

    @Test
    void successfulSignUPTest() {

        given()
                .contentType(ContentType.JSON)
                .body("{ \"email\": \"tobias.funke@reqres.in\", \"password\": \"city\" }")
                .when()
                .post("/api/register")
                .then()
                .log().body()
                .statusCode(200)
                .body("token", is(notNullValue()))
                .body("id", is(notNullValue()));
    }

    @Test
    void updateMethodTest() {

        given()
                .contentType(ContentType.JSON)
                .body("{ \"name\": \"morpheus\", \"job\": \"zion resident\" }")
                .when()
                .put("/api/register")
                .then()
                .log().body()
                .statusCode(200)
                .body("updatedAt", is(notNullValue()));
    }


    @Test
    void findByNameInResorcesListTest() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/unknown")
                .then()
                .log().body()
                .statusCode(200)
                .body("data.find { it.name == 'tigerlily' }.id", is(notNullValue()));
    }


    @Test
    void createJobTest() {

        given()
                .contentType(ContentType.JSON)
                .body("{ \"name\": \"morpheus\", \"job\": \"leader\" }")
                .when()
                .post("/api/users")
                .then()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"))
                .body("id", is(notNullValue()))
                .body("createdAt", is(notNullValue()));
    }

    @Test
    void verifyCertainUserIsRegistredTest() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/users?page=2")
                .then()
                .log().body()
                .statusCode(200)
                .body("data.find { it.first_name == 'Rachel' }.last_name", is("Howell"));
    }
}
