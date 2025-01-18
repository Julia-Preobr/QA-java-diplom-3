package web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {
    private static final String YANDEX_PATH = "C:\\Users\\Acer\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe";

    public static WebDriver createDriver(BrowserType browserType) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        if (browserType == BrowserType.CHROME) {
            WebDriverManager.chromedriver().setup();
        } else if (browserType == BrowserType.YANDEX) {
            WebDriverManager.chromedriver().browserVersion("130.0").setup();
            options.addArguments("--remote-allow-origins=*");
            options.setBinary(YANDEX_PATH);
        }
        return new ChromeDriver(options);
    }
}
