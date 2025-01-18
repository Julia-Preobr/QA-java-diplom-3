package tests;

import api.Base;
import api.UserApi;
import data.Login;
import data.User;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.yaml.snakeyaml.Yaml;
import pages.*;
import web.BrowserType;
import web.WebDriverFactory;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Map;

import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class BaseTest {

    protected String authToken;

    protected BrowserType browserType;

    protected WebDriver driver;

    private LoginPage loginPage;
    private HomePage homePage;
    private RegistrationPage registrationPage;
    private ProfilePage profilePage;
    private ForgotPasswordPage forgotPasswordPage;
    private ConstructorPage constructorPage;

    @Before
    public void setUp() {
        readProperties("tests.yml");

        initDriver(this.browserType);

        RestAssured.baseURI = Base.API_URL;
    }

    @Step("Инициализация драйвера, браузер: {0}")
    protected void initDriver(BrowserType browserType) {
        driver = WebDriverFactory.createDriver(browserType);
        driver.get("https://stellarburgers.nomoreparties.site/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Step("Получаем параметры тестов: {0}")
    protected void readProperties(String fileName) {
        Yaml yaml = new Yaml();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(fileName)) {
            Map<String, Object> properties = yaml.load(is);
            Map<String, Object> browserMap = (Map<String, Object>) properties.get("browser");
            if (browserMap != null) {
                Object browserType = browserMap.get("type");
                if (browserType != null) {
                    this.browserType = BrowserType.valueOf(browserType.toString());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Step("Генерация данных уникального пользователя")
    protected User getRandomUser() {
        // Генерация слйчайных данных для пользователя
        String username = RandomStringUtils.randomAlphanumeric(8, 15);  // уникальное имя
        String password = RandomStringUtils.randomAlphanumeric(8, 15);  // стандартный пароль
        String email = RandomStringUtils.randomAlphanumeric(8, 15).toLowerCase() + "@yandex.ru";  // email пользователя

        return new User(username, email, password);
    }

    @Step("Нажатие кнопки \"Личный кабинет\"")
    protected void clickProfilePageButton() {
        getHomePage().goToAccountPage();
    }

    @Step("Регистрация пользователя: {0}")
    protected void createDefinedUser(User user) {
        Assert.assertNotNull("Пользователь для тестов не задан", user);

        ValidatableResponse response =
                UserApi.createUser(user)
                        .assertThat()
                        .statusCode(SC_OK)
                        .body("success", equalTo(true))  // Успех
                        .body("user.email", equalTo(user.getEmail()))  // Проверка email
                        .body("user.name", equalTo(user.getName()))  // Проверка имени
                        .body("accessToken", notNullValue())  // Проверка наличия токенов
                        .body("refreshToken", notNullValue());

        // Сохраняем токен для дальнейших тестов
        authToken = response.extract().path("accessToken");
    }

    @Step("Вход пользователем: {0}")
    protected void loginUser(Login login) {
        ValidatableResponse response =
                UserApi.loginUser(login)
                        .assertThat()
                        .statusCode(SC_OK)
                        .body("success", equalTo(true))
                        .body("user.email", equalTo(login.getEmail()))  // Проверка email
                        .body("accessToken", notNullValue())  // Проверка наличия токенов
                        .body("refreshToken", notNullValue());

        // Сохраняем токен для дальнейших тестов
        authToken = response.extract().path("accessToken");
    }

    @Step("Проверка наличия активного пользователя, удаление пользователя")
    protected void deleteDefinedUser() {
        if (authToken != null) {
            deleteActiveUser();
        }
    }

    @Step("Удаление пользователя")
    protected void deleteActiveUser() {
        String authValue = authToken;

        authToken = null;

        UserApi.deleteUser(authValue)
                .assertThat()
                .statusCode(SC_ACCEPTED);  // Успешное удаление
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

    @Step("Проверить доступность кнопки \"Оформить заказ\"")
    protected void goToHomePageAndWaitForVisiblePurchase() {
        getRegistrationPage().goToHomePage();

        getHomePage().waitForPurchase();
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

    public ConstructorPage getConstructorPage() {
        if (constructorPage == null) {
            constructorPage = new ConstructorPage(driver);
        }
        return constructorPage;
    }
}
