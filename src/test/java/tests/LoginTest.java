package tests;

import data.Login;
import data.User;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import web.BrowserType;

@RunWith(Parameterized.class)
public class LoginTest extends BaseTest {

    public LoginTest(BrowserType browserType) {
        super(browserType);
    }

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    @DisplayName("вход по кнопке «Войти в аккаунт» на главной")
    public void testLoginWithHomePageAccountButton() {
        profilePageEnterToAccount();

        testUserSuccessfulLogin();
    }

    @Step("На главной странице нажать \"Войти в аккаунт\"")
    private void profilePageEnterToAccount() {
        getHomePage().clickToAccountLogin();
    }

    @Test
    @DisplayName("вход через кнопку «Личный кабинет»")
    public void testLoginWithProfileButton() {
        clickProfilePageButton();

        testUserSuccessfulLogin();
    }

    @Test
    @DisplayName("вход через кнопку в форме регистрации")
    public void testGoToRegisterPage() {
        clickProfilePageButton();

        clickLoginRegisterLink();

        User testUser = new User(
                getRandomStr(), getRandomStr() + "@yandex.ru", getRandomStr()
        );
        testUserRegister(testUser);
    }

    @Step("Нажатие ссылки \"Зарегистрироваться\"")
    private void clickLoginRegisterLink() {
        getLoginPage().goToRegisterPage();
    }

    @Test
    @DisplayName("вход через кнопку «Личный кабинет»")
    public void testLoginWithRecoverPassword() {
        clickProfilePageButton();

        testUserSuccessfulLogin();
    }

    @Step("Нажатие кнопки \"Личный кабинет\"")
    private void clickProfilePageButton() {
        getHomePage().goToAccountPage();
    }

    @Step("Вход с тестовым пользователем")
    public void testUserSuccessfulLogin() {
        tryUserLogin(testLogin);

        getLoginPage().waitForPurchase();
    }

    @Test
    public void testGoToForgotPasswordPage() {
        clickProfilePageButton();

        testUserLoginWithEmptyPassword();

        clickUserLoginForgotPasswordLogin();

        testUserSuccessfulLogin();
    }

    @Step("Вход с тестовым пользователем с пустым паролем")
    public void testUserLoginWithEmptyPassword() {
        tryUserLogin(new Login(testLogin.getEmail(), ""));

        getLoginPage().waitForForgotPassword();
    }

    @Step("Нажатие ссылки \"Восстановить пароль\" и ссылки \"Войти\" на форме восстановления пароля")
    public void clickUserLoginForgotPasswordLogin() {
        getLoginPage().goToForgotPasswordPage();

        getForgotPasswordPage().enter();
    }

    private void tryUserLogin(Login login) {
        // Вводим правильные данные для входа
        getLoginPage().enterEmail(login.getEmail());
        getLoginPage().enterPassword(login.getPassword());

        // Нажимаем кнопку "Войти"
        getLoginPage().clickLogin();
    }

    private String getRandomStr() {
        return RandomStringUtils.randomAlphanumeric(8, 15);
    }

    @After
    public void tearDown() {
        super.tearDown();
    }
}
