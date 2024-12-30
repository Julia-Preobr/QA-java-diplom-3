package tests;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.HomePage;
import pages.LoginPage;
import web.BrowserType;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    public LoginTest(BrowserType browserType) {
        super(browserType);
    }

    @Before
    public void setUp() {
        super.setUp();

        initPages();
    }

    @Step("Инициализация объектов страниц")
    protected void initPages() {
        loginPage = new LoginPage(driver);
    }

    @Step("Вход с тестовым пользователем")
    public void testUserLogin() {
        // Вводим правильные данные для входа
        loginPage.enterEmail(testLogin.getEmail());
        loginPage.enterPassword(testLogin.getPassword());

        // Нажимаем кнопку "Войти"
        loginPage.clickLogin();

        loginPage.waitForPurchase();
    }

    @Step("На главной странице нажать \"Войти в аккаунт\"")
    private void profilePageEnterToAccount() {
        loginPage.goToHomePage();
        HomePage homePage = new PageFactory().createHomePage();
        homePage.clickToAccountLogin();
    }

    @Test
    @DisplayName("вход по кнопке «Войти в аккаунт» на главной")
    public void testSuccessfulLogin() {
        profilePageEnterToAccount();

        testUserLogin();
    }

    @Test
    public void testUnsuccessfulLoginWithInvalidCredentials() {
        // Вводим неверные данные для входа
        loginPage.enterEmail(testLogin.getEmail());
        loginPage.enterPassword(testLogin.getPassword());

        // Нажимаем кнопку "Войти"
        loginPage.clickLogin();

        // Проверяем, что отображается сообщение об ошибке
        // Например, проверка наличия сообщения об ошибке или редиректа на страницу входа
        assertTrue("Error message not displayed", driver.getPageSource().contains("Неверный логин или пароль"));
    }

    @Test
    public void testGoToRegisterPage() {
        // Переход на страницу регистрации
        loginPage.goToRegisterPage();

        // Проверяем, что мы перешли на страницу регистрации
        assertTrue("Not on register page", driver.getCurrentUrl().contains("/register"));
    }

    @Test
    public void testGoToForgotPasswordPage() {
        // Переход на страницу восстановления пароля
        loginPage.goToForgotPasswordPage();

        // Проверяем, что мы перешли на страницу восстановления пароля
        assertTrue("Not on forgot password page", driver.getCurrentUrl().contains("/forgot-password"));
    }

    @Test
    public void testGoToConstructor() {
        // Переход в конструктор
        loginPage.goToConstructor();

        // Проверяем, что мы перешли в конструктор
        assertTrue("Not on constructor page", driver.getCurrentUrl().contains("/constructor"));
    }

    @Test
    public void testGoToHomePage() {
        // Переход на главную страницу
        loginPage.goToHomePage();

        // Проверяем, что мы перешли на главную страницу
        assertTrue("Not on home page", driver.getCurrentUrl().equals("expectedHomePageURL"));
    }

    @After
    public void tearDown() {
        // Закрытие браузера после выполнения тестов
        driver.quit();

        super.tearDown();
    }
}
