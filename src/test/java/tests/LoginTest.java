package tests;

import data.Login;
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

        userSuccessfulLogin();
    }

    @Test
    @DisplayName("вход через кнопку «Личный кабинет»")
    public void testLoginWithProfileButton() {
        clickProfilePageButton();

        userSuccessfulLogin();
    }

    @Test
    @DisplayName("вход через кнопку в форме регистрации")
    public void testGoToRegisterPage() {
        clickProfilePageButton();

        clickLoginRegisterLink();

        clickRegisterPageLogin();
    }

    @Step("Вход с тестовым пользователем")
    public void userSuccessfulLogin() {
        tryUserLogin(testLogin);

        getHomePage().waitForPurchase();
    }

    @Step("Пройти по ссылке \"Войти\" на форме регистрации")
    public void clickRegisterPageLogin() {
        getRegistrationPage().enter();
    }

    @Test
    @DisplayName("вход через кнопку в форме восстановления пароля")
    public void testLoginWithRecoverPassword() {
        clickProfilePageButton();

        userLoginWithShortPassword();

        clickUserLoginForgotPasswordLogin();

        userSuccessfulLogin();
    }

    @Step("Вход с тестовым пользователем с коротким (до 6 символов) паролем")
    protected void userLoginWithShortPassword() {
        tryUserLogin(new Login(testLogin.getEmail(), RandomStringUtils.randomAlphanumeric(1, 6)));

        getLoginPage().waitForForgotPassword();
    }

    @Step("Нажатие ссылки \"Восстановить пароль\" и ссылки \"Войти\" на форме восстановления пароля")
    public void clickUserLoginForgotPasswordLogin() {
        getLoginPage().goToForgotPasswordPage();

        getForgotPasswordPage().enter();
    }

    private String getRandomStr() {
        return RandomStringUtils.randomAlphanumeric(8, 15);
    }

    @After
    public void tearDown() {
        super.tearDown();
    }
}
