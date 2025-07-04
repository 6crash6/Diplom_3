package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.*;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.Matchers.equalTo;

public class UserAuthorizationTest extends BaseTest {


    @Before
    @DisplayName("test create user by API")
    @Description("Создаётся успешно пользователь через API")
    public void createUser() {

        ExtractableResponse<Response> extract = userClient.createUser(user).log().all()
                .assertThat()
                .statusCode(HTTP_OK)
                .body("success", equalTo(true))
                .extract();
        accessToken = extract.path("accessToken");
        refreshToken = extract.path("refreshToken");
    }

    @Test
    @DisplayName("test log in from main page")
    @Description("Проверка авторизации пользователя с главной страницы")
    public void logInFromMainPageTest() {
        new MainPage(webDriver)
                .signInButtonClick();
        new AuthorizationPage(webDriver)
                .logIn(email, password);
        new MainPage(webDriver)
                .checkoutButtonAvailable();
    }

    @Test
    @DisplayName("test log in from personal account page")
    @Description("Проверка авторизации пользователя из Личного Кабинета")
    public void logInFromPersonalAccountPageTest() {
        new MainPage(webDriver)
                .personalAccountButtonClick();
        new PersonalAccountPage(webDriver)
                .logIn(email, password);
        new MainPage(webDriver)
                .checkoutButtonAvailable();
    }

    @Test
    @DisplayName("test log in from registration page")
    @Description("Проверка авторизации пользователя с формы регистрации")
    public void logInFromRegistrationPage() {
        new MainPage(webDriver)
                .signInButtonClick();
        new AuthorizationPage(webDriver)
                .registrationButtonClick();
        new RegistrationPage(webDriver)
                .enterButtonClick();
        new AuthorizationPage(webDriver)
                .logIn(email, password);
        new MainPage(webDriver)
                .checkoutButtonAvailable();
    }

    @Test
    @DisplayName("test log in from password recovery page")
    @Description("Проверка авторизации пользователя в форме восстановления пароля")
    public void logInFromPasswordRecoveryPage() {
        new MainPage(webDriver)
                .signInButtonClick();
        new AuthorizationPage(webDriver)
                .passwordRecoveryButtonClick();
        new PasswordRecoveryPage(webDriver)
                .enterButtonClick();
        new AuthorizationPage(webDriver)
                .logIn(email, password);
        new MainPage(webDriver)
                .checkoutButtonAvailable();
    }

    @After
    @DisplayName("test delete user by API")
    @Description("Удаляется успешно пользователь через API")
    public void deleteUserTest() {
        if (accessToken != null) {
            userClient.deleteUser(accessToken).log().all();
        }
    }
}