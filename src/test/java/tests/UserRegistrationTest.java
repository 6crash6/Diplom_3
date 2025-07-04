package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import pageobject.AuthorizationPage;
import pageobject.MainPage;
import pageobject.RegistrationPage;
import pojo.Credentials;


public class UserRegistrationTest extends BaseTest {

    protected String accessToken;
    private final String email = user.getEmail();
    private final String password = user.getPassword();
    private final String name = user.getName();


    @Test
    @DisplayName("check create user")
    @Description("Проверка создания пользователя")
    public void checkCreateUser() {
        new MainPage(webDriver)
                .signInButtonClick();
        new AuthorizationPage(webDriver)
                .registrationButtonClick();
        new RegistrationPage(webDriver)
                .fillRegistrationFields(name, email, password);
        new AuthorizationPage(webDriver)
                .fieldEmailAvailable();

        Credentials creds = Credentials.from(user);
        accessToken = userClient.logIn(creds).log().all()
                .extract()
                .path("accessToken");
    }

    @Test
    @DisplayName("check create user with incorrect password")
    @Description("Проверка создания пользователя с некорректным паролем")
    public void checkCreateUserWithIncorrectPassword() {
        new MainPage(webDriver)
                .signInButtonClick();
        new AuthorizationPage(webDriver)
                .registrationButtonClick();
        RegistrationPage registrationPage = new RegistrationPage(webDriver);
        registrationPage.fillRegistrationFieldsWithIncorrectPassword(name, email, incorrectPassword);
    }


    @After
    @DisplayName("check delete user by API")
    @Description("Удаляется успешно пользователь через API")
    public void deleteUserTest() {
        if (accessToken != null) {
            userClient.deleteUser(accessToken).log().all();
        }
    }
}