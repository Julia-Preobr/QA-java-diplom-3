package tests;

import data.Login;
import data.User;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LoginTest extends BaseTest {
    private User testUser;

    @Before
    public void setUp() {
        super.setUp();

        testUser = new User(
                RandomStringUtils.randomAlphanumeric(8, 15),
                RandomStringUtils.randomAlphabetic(8, 12) + "@yandex.ru",
                RandomStringUtils.randomAlphanumeric(8, 15)
        );
        createDefinedUser(testUser);
    }

    @Test
    @DisplayName("вход по кнопке «Войти в аккаунт» на главной")
    @Description("вход по кнопке «Войти в аккаунт» на главной")
    public void testLoginWithHomePageAccountButton() {
        profilePageEnterToAccount();

        userSuccessfulLogin();

        Assert.assertTrue("Нет кнопки \"Оформить заказ\"", getHomePage().isPurchaseButtonAvailable());
    }

    @Test
    @DisplayName("вход через кнопку «Личный кабинет»")
    @Description("вход через кнопку «Личный кабинет»")
    public void testLoginWithProfileButton() {
        clickProfilePageButton();

        userSuccessfulLogin();

        Assert.assertTrue("Нет кнопки \"Оформить заказ\"", getHomePage().isPurchaseButtonAvailable());
    }

    @Test
    @DisplayName("вход через кнопку в форме регистрации")
    @Description("вход через кнопку в форме регистрации")
    public void testGoToRegisterPage() {
        clickProfilePageButton();

        clickLoginRegisterLink();

        clickRegisterPageLogin();

        userSuccessfulLogin();

        Assert.assertTrue("Нет кнопки \"Оформить заказ\"", getHomePage().isPurchaseButtonAvailable());
    }

    @Step("Вход с тестовым пользователем")
    public void userSuccessfulLogin() {
        tryUserLogin(testUser);

        getHomePage().waitForPurchase();
    }

    @Step("Пройти по ссылке \"Войти\" на форме регистрации")
    public void clickRegisterPageLogin() {
        getRegistrationPage().enter();
    }

    @Test
    @DisplayName("вход через кнопку в форме восстановления пароля")
    @Description("вход через кнопку в форме восстановления пароля")
    public void testLoginWithRecoverPassword() {
        clickProfilePageButton();

        userLoginWithShortPassword();

        clickUserLoginForgotPasswordLogin();

        userSuccessfulLogin();

        Assert.assertTrue("Нет кнопки \"Оформить заказ\"", getHomePage().isPurchaseButtonAvailable());
    }

    @Step("Вход с тестовым пользователем с коротким (до 6 символов) паролем")
    protected void userLoginWithShortPassword() {
        tryUserLogin(new Login(testUser.getEmail(), RandomStringUtils.randomAlphanumeric(1, 6)));

        getLoginPage().waitForForgotPassword();
    }

    @Step("Нажатие ссылки \"Восстановить пароль\" и ссылки \"Войти\" на форме восстановления пароля")
    public void clickUserLoginForgotPasswordLogin() {
        getLoginPage().goToForgotPasswordPage();

        getForgotPasswordPage().enter();
    }

    @After
    public void tearDown() {
        deleteDefinedUser();
        super.tearDown();
    }
}
