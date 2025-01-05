package tests;

import api.Base;
import data.Login;
import data.User;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.*;
import web.BrowserType;
import web.WebDriverFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class BaseTest {
    private static final String APP_USER = "преображенская_11@gmail.com";
    private static final String APP_PASSWORD = "123456789";

    // todo switch to testUser with random data
    protected static Login testLogin = new Login(APP_USER, APP_PASSWORD);

    protected String authToken;

    protected BrowserType browserType;

    protected WebDriver driver;

    private LoginPage loginPage;
    private HomePage homePage;
    private RegistrationPage registrationPage;
    private ProfilePage profilePage;
    private ForgotPasswordPage forgotPasswordPage;
    private ConstructorPage constructorPage;

    public BaseTest(BrowserType browserType) {
        this.browserType = browserType;
    }

    @Before
    public void setUp() {
        initDriver(this.browserType);

        RestAssured.baseURI = Base.API_URL;
    }

    @Step("Инициализация драйвера, браузер: {0}")
    protected void initDriver(BrowserType browserType) {
        driver = WebDriverFactory.createDriver(browserType);
        driver.get("https://stellarburgers.nomoreparties.site/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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

        ValidatableResponse response = given()
                .filter(new AllureRestAssured())
                .header("Content-type", "application/json")
                .body(user)
                .log().all()
                .when()
                .post("/auth/register")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true))  // Успех
                .body("user.email", equalTo(user.getEmail()))  // Проверка email
                .body("user.name", equalTo(user.getName()))  // Проверка имени
                .body("accessToken", notNullValue())  // Проверка наличия токенов
                .body("refreshToken", notNullValue());

        // Сохраняем токен для дальнейших тестов
        authToken = response.extract().path("accessToken");
    }

    protected void deleteDefinedUser(User user) {
        if (authToken != null) {
            deleteActiveUser(user);
        }
    }

    @Step("Удаление пользователя: {0}")
    protected void deleteActiveUser(User user) {
        String authValue = authToken;

        authToken = null;

        given()
                .filter(new AllureRestAssured())
                .header("Authorization", authValue)  // Авторизация с использованием токена
                .log().all()
                .when()
                .delete("/auth/user")
                .then()
                .log().all()
                .assertThat()
                .statusCode(202);  // Успешное удаление
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
    protected void checkIfHomePageHasPurchase() {
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

    @Parameterized.Parameters(name = "Запуск в браузере {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {BrowserType.CHROME},
                {BrowserType.YANDEX}
        });
    }
}
