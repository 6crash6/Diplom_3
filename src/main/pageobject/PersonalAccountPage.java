package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalAccountPage {

    //Поле для ввода "Email"
    private static final By EMAIL =
            By.xpath(".//label[text()='Email']/parent::div/input[@class='text input__textfield text_type_main-default']");
    //Поле для ввода "Пароль"
    private static final By PASSWORD = By.xpath(".//label[text()='Пароль']/parent::div/input");
    //Кнопка "Войти"
    private static final By ENTER_BUTTON = By.xpath(".//button[text()='Войти']");
    //Заголовок вкладки "Профиль" в Личном Кабинете
    private static final By PROFILE_TAB_TITLE = By.xpath(".//a[text()='Профиль']");
    private static final By CONSTRUCTOR_BUTTON = By.xpath(".//p[text()='Конструктор']");
    private static final By STELLAR_BURGER_LOGO =
            By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");
    private static final By EXIT_BUTTON = By.xpath(".//button[text()='Выход']");
    private final WebDriver webDriver;

    public PersonalAccountPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Заполнение полей для авторизации и клик по кнопке Войти")
    public PersonalAccountPage logIn(String email, String password) {
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(EMAIL)).sendKeys(email);
        webDriver.findElement(PASSWORD).sendKeys(password);
        webDriver.findElement(ENTER_BUTTON).click();
        return this;
    }

    @Step("Дожидаемся вкладку Профиль и клик по кнопке Конструктор")
    public PersonalAccountPage goToConstructorByConstructorButton() {
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(PROFILE_TAB_TITLE));
        webDriver.findElement(CONSTRUCTOR_BUTTON).click();
        return this;
    }

    @Step("Дожидаемся вкладку Профиль и клик по кнопке Логотип")
    public PersonalAccountPage goToConstructorByLogoButton() {
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(PROFILE_TAB_TITLE));
        webDriver.findElement(STELLAR_BURGER_LOGO).click();
        return this;
    }

    @Step("Дожидаемся вкладку Профиль и клик по кнопке Выход")
    public PersonalAccountPage exitFromPersonalAccount() {
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(PROFILE_TAB_TITLE));
        webDriver.findElement(EXIT_BUTTON).click();
        return this;
    }
}