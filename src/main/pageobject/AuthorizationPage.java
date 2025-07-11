package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthorizationPage {

    //Кнопка "Зарегистрироваться"
    private static final By REGISTRATION_BUTTON = By.xpath(".//a[text()='Зарегистрироваться']");
    //Поле для ввода "Email"
    private static final By EMAIL = By.xpath(".//label[text()='Email']/parent::div/input[@class='text input__textfield text_type_main-default']");
    //Поле для ввода "Пароль"
    private static final By PASSWORD = By.xpath(".//label[text()='Пароль']/parent::div/input");
    //Кнопка "Войти"
    private static final By ENTER_BUTTON = By.xpath(".//button[text()='Войти']");
    //Кнопка "Восстановить пароль"
    private static final By PASSWORD_RECOVERY_BUTTON = By.xpath(".//a[text()='Восстановить пароль']");

    private final WebDriver webDriver;

    public AuthorizationPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Клик по кнопке Зарегистрироваться")
    public AuthorizationPage registrationButtonClick() {
        WebElement regButton = webDriver.findElement(REGISTRATION_BUTTON);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", regButton);
        regButton.click();
        return this;
    }

    @Step("Дожидаемся видимости поля email")
    public AuthorizationPage fieldEmailAvailable() {
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(EMAIL));
        return this;
    }

    @Step("Заполнение полей для авторизации и клик по кнопке Войти")
    public AuthorizationPage logIn(String email, String password) {
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(EMAIL)).sendKeys(email);
        webDriver.findElement(PASSWORD).sendKeys(password);
        webDriver.findElement(ENTER_BUTTON).click();
        return this;
    }

    @Step("Клик по кнопке Восстановить пароль")
    public AuthorizationPage passwordRecoveryButtonClick() {
        WebElement passRecButton = webDriver.findElement(PASSWORD_RECOVERY_BUTTON);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", passRecButton);
        passRecButton.click();
        return this;
    }
}