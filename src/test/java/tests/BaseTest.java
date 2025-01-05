package tests;

import data.Login;
import data.User;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.*;
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

    private LoginPage loginPage;
    private HomePage homePage;
    private RegistrationPage registrationPage;
    private ProfilePage profilePage;
    private ForgotPasswordPage forgotPasswordPage;

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

    @Step("Регистрация пользователя: {0}")
    protected void userRegister(User user) {
        getRegistrationPage().enterEmail(user.getEmail());
        getRegistrationPage().enterPassword(user.getPassword());
        getRegistrationPage().enterName(user.getName());
        getRegistrationPage().clickRegister();
    }

    @Step("На главной странице нажать \"Войти в аккаунт\"")
    protected void profilePageEnterToAccount() {
        getHomePage().clickToAccountLogin();
    }

    @Step("Нажатие ссылки \"Зарегистрироваться\"")
    protected void clickLoginRegisterLink() {
        getLoginPage().goToRegisterPage();
    }

    protected void tryUserLogin(Login login) {
        // Вводим правильные данные для входа
        getLoginPage().enterEmail(login.getEmail());
        getLoginPage().enterPassword(login.getPassword());

        // Нажимаем кнопку "Войти"
        getLoginPage().clickLogin();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage(driver);
        }
        return homePage;
    }

    public RegistrationPage getRegistrationPage() {
        if (registrationPage == null) {
            registrationPage = new RegistrationPage(driver);
        }
        return registrationPage;
    }

    public ProfilePage getProfilePage() {
        if (profilePage == null) {
            profilePage = new ProfilePage(driver);
        }
        return profilePage;
    }

    public ForgotPasswordPage getForgotPasswordPage() {
        if (forgotPasswordPage == null) {
            forgotPasswordPage = new ForgotPasswordPage(driver);
        }
        return forgotPasswordPage;
    }

    @Parameterized.Parameters(name = "Запуск в браузере {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {BrowserType.CHROME},
                {BrowserType.YANDEX}
        });
    }
}
