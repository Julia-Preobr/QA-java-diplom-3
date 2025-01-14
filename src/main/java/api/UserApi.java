package api;

import data.Login;
import data.User;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserApi {

    public static final String BEARER_HEADER = "Bearer ";

    public static ValidatableResponse createUser(User user) {
        return given()
                .filter(new AllureRestAssured())
                .header("Content-type", "application/json")
                .body(user)
                .log().all()
                .when()
                .post("/auth/register")
                .then()
                .log().all();
    }

    public static ValidatableResponse loginUser(Login login) {
        return given()
                .filter(new AllureRestAssured())
                .header("Content-type", "application/json")
                .body(login)
                .log().all()
                .when()
                .post("/auth/login")
                .then()
                .log().all();
    }

    public static ValidatableResponse deleteUser(String authToken) {
        if (!authToken.startsWith(BEARER_HEADER)) {
            authToken = BEARER_HEADER + authToken;
        }
        return given()
                .filter(new AllureRestAssured())
                .header("Authorization", authToken)  // Авторизация с использованием токена
                .log().all()
                .when()
                .delete("/auth/user")
                .then()
                .log().all();
    }
}
