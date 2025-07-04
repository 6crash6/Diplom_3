package clients;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import pojo.Credentials;
import pojo.User;

public class UserClient extends Client{

    @Step("Запрос на создание пользователя")
    public ValidatableResponse createUser(User user) {
        return spec()
                .body(user)
                .when()
                .post("/auth/register")
                .then().log().all();
    }

    @Step("Запрос на авторизацию существующего пользователя")
    public ValidatableResponse logIn(Credentials creds) {
        return spec()
                .body(creds)
                .when()
                .post("/auth/login")
                .then().log().all();
    }

    @Step("Запрос на удаление пользователя")
    public ValidatableResponse deleteUser(String accessToken) {
        return spec()
                .header("Authorization", accessToken)
                .when()
                .delete("/auth/user")
                .then().log().all();
    }
}