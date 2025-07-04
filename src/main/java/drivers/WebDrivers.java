package drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class WebDrivers {

    public static WebDriver getBs() {
        WebDriver webDriver;

        String browserName = System.getProperty("browserName");

        switch (browserName) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
                break;
            case "yandexBrowser":
                System.setProperty("webdriver.http.factory", "jdk-http-client");
                ChromeDriverService service = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File("D:\\QA-java-diplom-1-main\\Diplom_3\\src\\main\\resources\\yandexdriver.exe"))
                        .build();
                ChromeOptions options = new ChromeOptions()
                        .setBinary("C:\\Users\\Sergey\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
                try {
                    webDriver = new ChromeDriver(service, options);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("Failed to initialize WebDriver", e);
                }
                break;
            default:
                throw new RuntimeException("Browser is not detected");
        }
        return webDriver;
    }
}