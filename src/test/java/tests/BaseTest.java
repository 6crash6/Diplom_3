package tests;

import clients.UserClient;
import data.GeneratorCreds;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import pageobject.MainPage;
import pojo.User;
import drivers.WebDrivers;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected final GeneratorCreds generatorCreds = new GeneratorCreds();
    protected final UserClient userClient = new UserClient();
    protected final String incorrectPassword = RandomStringUtils.randomAlphabetic(1, 5);
    protected String accessToken;
    protected String refreshToken;
    protected WebDriver webDriver;
    protected User user = generatorCreds.randomUser();
    protected final String email = user.getEmail();
    protected final String password = user.getPassword();

    @Before
    public void setUp() {
        webDriver = WebDrivers.getBs();

        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        MainPage mainPage = new MainPage(webDriver);
        mainPage.open();
    }
    @After
    public void tearDown() {
        webDriver.quit();
    }
}