package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.AuthorizationPage;
import pageobject.MainPage;
import pageobject.PersonalAccountPage;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.Matchers.equalTo;

public class PersonalAccountTest extends BaseTest {


    @Before
    @DisplayName("Create user by API")
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
    @DisplayName("test go to personal account page")
    @Description("Проверка перехода в Личный кабинет")
    public void goToPersonalAccountTest() {
        new MainPage(webDriver)
                .signInButtonClick();
        new AuthorizationPage(webDriver)
                .logIn(email, password);
        new MainPage(webDriver)
                .personalAccountButtonClick();
    }

    @Test
    @DisplayName("test go to constructor by constructor button")
    @Description("Проверка перехода из личного кабинета в конструктор")
    public void goFromPersonalAccountByConstructorButtonTest() {
        new MainPage(webDriver)
                .signInButtonClick();
        new AuthorizationPage(webDriver)
                .logIn(email, password);
        new MainPage(webDriver)
                .personalAccountButtonClick();
        new PersonalAccountPage(webDriver)
                .goToConstructorByConstructorButton();
        new MainPage(webDriver)
                .checkoutButtonAvailable();
    }

    @Test
    @DisplayName("test go to constructor by logo button")
    @Description("Проверка перехода по клику на «Конструктор» и на логотип Stellar Burgers")
    public void goFromPersonalAccountByLogoButtonTest() {
        new MainPage(webDriver)
                .signInButtonClick();
        new AuthorizationPage(webDriver)
                .logIn(email, password);
        new MainPage(webDriver)
                .personalAccountButtonClick();
        new PersonalAccountPage(webDriver)
                .goToConstructorByLogoButton();
        new MainPage(webDriver)
                .checkoutButtonAvailable();
    }

    @Test
    @DisplayName("test exit from personal account")
    @Description("Проверка выхода по кнопке «Выйти» в личном кабинете")
    public void exitFromPersonalAccount() {
        new MainPage(webDriver)
                .signInButtonClick();
        new AuthorizationPage(webDriver)
                .logIn(email, password);
        new MainPage(webDriver)
                .personalAccountButtonClick();
        new PersonalAccountPage(webDriver)
                .exitFromPersonalAccount();
        new AuthorizationPage(webDriver)
                .fieldEmailAvailable();
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