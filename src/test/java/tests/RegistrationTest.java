package tests;

import io.qameta.allure.Step;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.RegistrationPage;
import web.BrowserType;

@RunWith(Parameterized.class)
public class RegistrationTest extends BaseTest {

    private RegistrationPage registrationPage;

    public RegistrationTest(BrowserType browserType) {
        super(browserType);
    }

    @Before
    public void setUp() {
        super.setUp();

        registrationPage = new RegistrationPage(driver);
    }

    @Test
    @Step("Проверка успешной регистрации")
    public void testSuccessfulRegistration() {
        registrationPage.enterEmail("test@example.com");
        registrationPage.enterPassword("password123");
        registrationPage.enterName("Test User");
        registrationPage.clickRegister();

        // todo
        //String successMessage = registrationPage.getSuccessMessage();
        //Assert.assertEquals("Регистрация прошла успешно", successMessage);
    }

    @Test
    @Step("Проверка ошибки для некорректного пароля")
    public void testPasswordTooShort() {
        registrationPage.enterEmail("test@example.com");
        registrationPage.enterPassword("short");
        registrationPage.enterName("Test User");
        registrationPage.clickRegister();

        // todo
        //String errorMessage = registrationPage.incorrectPasswordEntered();
        //Assert.assertEquals("Пароль слишком короткий", errorMessage);
    }
}