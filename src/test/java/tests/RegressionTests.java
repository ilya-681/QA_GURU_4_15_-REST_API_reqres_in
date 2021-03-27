package tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;
import static utils.FileUtils.readStringFromFile;


public class RegressionTests extends TestBase {

    @Test
    void successfulSignUpTest() {

        given()
                .contentType(JSON)
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
                .contentType(JSON)
                .body("{ \"name\": \"morpheus\", \"job\": \"zion resident\" }")
                .when()
                .put("/api/register")
                .then()
                .log().body()
                .statusCode(200)
                .body("updatedAt", is(notNullValue()));
    }


    @Test
    void findByNameInResourcesListTest() {
        given()
                .contentType(JSON)
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
                .contentType(JSON)
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
    void verifyCertainUserIsRegisteredTest() {
        given()
                .contentType(JSON)
                .when()
                .get("/api/users?page=2")
                .then()
                .log().body()
                .statusCode(200)
                .body("data.find { it.first_name == 'Rachel' }.last_name", is("Howell"));
    }

    @Test
    void verifyCertainUserIsRegisteredTest2() {
        given()
                .contentType(JSON)
                .when()
                .get("/api/users?page=2")
                .then()
                .log().body()
                .statusCode(200)
                .body("page", is(2));
    }

    @Test
    void successLoginWithDataInFileTest() {
        String data = readStringFromFile("./src/test/resources/login_data.txt");
        given()
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .post("/api/login")
                .then()
                .statusCode(200)
                .log().body()
                .body("token", is(notNullValue()));
    }
}
