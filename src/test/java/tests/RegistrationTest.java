package tests;

import data.User;
import io.qameta.allure.Step;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import web.BrowserType;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

@RunWith(Parameterized.class)
public class RegistrationTest extends BaseTest {

    public RegistrationTest(BrowserType browserType) {
        super(browserType);
    }

    @Test
    @Step("Проверка успешной регистрации")
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
    }

    @Test
    @Step("Проверка ошибки для некорректного (короткого) пароля")
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
    }
}
