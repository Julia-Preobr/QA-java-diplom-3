package tests;

import data.Login;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;
import web.BrowserType;
import web.WebDriverFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

public class BaseTest {
    private static final String APP_USER = "преображенская_11@gmail.com";
    private static final String APP_PASSWORD = "123456789";

    protected static Login testLogin = new Login(APP_USER, APP_PASSWORD);

    protected BrowserType browserType;

    protected WebDriver driver;

    public BaseTest(BrowserType browserType) {
        this.browserType = browserType;
    }

    @Before
    public void setUp() {
        initDriver(this.browserType);
    }

    @Step("Инициализация драйвера, браузер: {0}")
    protected void initDriver(BrowserType browserType) {
        driver = WebDriverFactory.createDriver(browserType);
        driver.get("https://stellarburgers.nomoreparties.site/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Parameterized.Parameters(name = "Запуск в браузере {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {BrowserType.CHROME},
                {BrowserType.YANDEX}
        });
    }

    public class PageFactory {
        public LoginPage createLoginPage() {
            return new LoginPage(driver);
        }

        public HomePage createHomePage() {
            return new HomePage(driver);
        }

        public ProfilePage createProfilePage() {
            return new ProfilePage(driver);
        }
    }
}
