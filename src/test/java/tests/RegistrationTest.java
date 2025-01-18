package tests;

import data.User;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class RegistrationTest extends BaseTest {

    @Test
    @DisplayName("Проверка успешной регистрации")
    @Description("Проверка успешной регистрации")
    public void testSuccessfulRegistration() {
        profilePageEnterToAccount();

        clickLoginRegisterLink();

        User testUser = new User(
                randomAlphanumeric(8, 15),
                randomAlphanumeric(8, 15) + "@yandex.ru",
                randomAlphanumeric(12, 16)
        );

        userRegister(testUser);

        getLoginPage().waitForLoginButton();

        tryUserLogin(testUser);

        goToHomePageAndWaitForVisiblePurchase();
        loginUser(testUser);

        Assert.assertTrue("Нет кнопки \"Оформить заказ\"", getHomePage().isPurchaseButtonAvailable());

        deleteDefinedUser();
    }

    @Test
    @DisplayName("Проверка ошибки для некорректного (короткого) пароля")
    @Description("Проверка ошибки для некорректного (короткого) пароля")
    public void testPasswordTooShort() {
        profilePageEnterToAccount();

        clickLoginRegisterLink();

        User testUser = new User(
                randomAlphanumeric(8, 15),
                randomAlphanumeric(8, 15) + "@yandex.ru",
                randomAlphanumeric(1, 5)
        );

        userRegister(testUser);

        getRegistrationPage().waitForIncorrectPasswordEntered();

        Assert.assertTrue(
                "Нет ошибки \"Некорректный пароль\"",
                getRegistrationPage().isIncorrectPasswordEnteredVisible()
        );
    }
}
