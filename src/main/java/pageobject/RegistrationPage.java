package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {

    private final WebDriver webDriver;;

    //Поле для ввода "Имя"
    private static final By NAME = By.xpath(".//label[text()='Имя']/parent::div/input");
    //Поле для ввода "Email"
    private static final By EMAIL =
            By.xpath(".//label[text()='Email']/parent::div/input[@class='text input__textfield text_type_main-default']");
    //Поле для ввода "Пароль"
    private static final By PASSWORD = By.xpath(".//label[text()='Пароль']/parent::div/input");
    //Кнопка "Зарегистрироваться" на странице регистрации
    private static final By REGISTRATION_ACCEPT_BUTTON =
            By.xpath(".//button[text()='Зарегистрироваться']");
    private static final By ENTER_BUTTON = By.xpath(".//a[text()='Войти']");
    private static final By ERROR_MESSAGE_INCORRECT_PASSWORD =
            By.xpath(".//label[text()='Пароль']/parent::div/parent::div/p[text()='Некорректный пароль']");

    public RegistrationPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Заполнение полей для регистрации и клик по кнопке Зарегистрироваться")
    public void fillRegistrationFields(String name, String email, String password) {
        new WebDriverWait(webDriver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(NAME)).sendKeys(name);
        webDriver.findElement(EMAIL).sendKeys(email);
        webDriver.findElement(PASSWORD).sendKeys(password);
        webDriver.findElement(REGISTRATION_ACCEPT_BUTTON).click();
    }

    @Step("Заполнение полей с некорректным паролем для регистрации и клик по кнопке Зарегистрироваться")
    public void fillRegistrationFieldsWithIncorrectPassword(String name, String email, String incorrectPassword) {
        new WebDriverWait(webDriver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(NAME)).sendKeys(name);
        webDriver.findElement(EMAIL).sendKeys(email);
        webDriver.findElement(PASSWORD).sendKeys(incorrectPassword);
        webDriver.findElement(REGISTRATION_ACCEPT_BUTTON).click();
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE_INCORRECT_PASSWORD));
    }

    @Step("Клик по кнопке Войти")
    public RegistrationPage enterButtonClick() {
        WebElement enterButton = webDriver.findElement(ENTER_BUTTON);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", enterButton);
        enterButton.click();
        return this;
    }
}