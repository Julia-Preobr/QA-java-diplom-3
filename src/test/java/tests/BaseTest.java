package tests;

import data.Login;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected Browser browser;

    private Configuration configuration;
    protected WebDriver driver;
    protected Login testLogin;

    public BaseTest(Browser browser) throws ConfigurationException {
        this.browser = browser;

        Configurations configurations = new Configurations();
        this.configuration = configurations.properties(
                getClass().getResource("tests.properties")
        );
        this.testLogin = new Login(
                this.configuration.getString("app.user"),
                this.configuration.getString("app.password")
        );
    }

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setUp() {
        if (browser == Browser.CHROME) {
            System.setProperty("webdriver.chrome.driver", readProperty("chrome.path"));
            driver = new ChromeDriver();
        } else if (browser == Browser.YANDEX) {
            System.setProperty("webdriver.gecko.driver", readProperty("yandex.path"));
            driver = new ChromeDriver();
        }

        // Настройка для Chrome
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://stellarburgers.nomoreparties.site");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    protected String readProperty(String name) {
        Assert.assertNotNull("Не найден файл свойств tests.properties", configuration);

        return configuration.getString(name);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {Browser.CHROME},
                {Browser.YANDEX}
        });
    }

    public enum Browser {
        CHROME, YANDEX
    }
}

