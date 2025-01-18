package tests;

import data.User;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TransitionsTest extends BaseTest {
    protected static User testUser;

    @Before
    public void setUp() {
        super.setUp();

        testUser = getRandomUser();
        createDefinedUser(testUser);

        profilePageEnterToAccount();
        getLoginPage().waitForLoginButton();

        loginWithTestUser(testUser);
    }

    @Step("Вход тестовым пользователем: {0}")
    private void loginWithTestUser(User user) {
        tryUserLogin(user);
        goToHomePageAndWaitForVisiblePurchase();
    }

    @Test
    @DisplayName("Проверка перехода по клику на \"Личный кабинет\"")
    @Description("Проверка перехода по клику на \"Личный кабинет\"")
    public void testNavigateWithProfileButton() {
        goToUserProfile();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор (по кнопке \"Конструктор\")")
    @Description("Переход из личного кабинета в конструктор (по кнопке \"Конструктор\")")
    public void testNavigateFromProfileToConstructor1() {
        goToUserProfile();

        navigateToConstructor1();
    }

    @Step("Переход в \"Конструктор\" по кнопке \"Конструктор\"")
    public void navigateToConstructor1() {
        getProfilePage().goToConstructor();
        getHomePage().waitForPurchase();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор (по клику на логотип \"Stellar Burgers\")")
    @Description("Переход из личного кабинета в конструктор (по клику на логотип \"Stellar Burgers\")")
    public void testNavigateFromProfileToConstructor2() {
        goToUserProfile();

        navigateToConstructor2();
    }

    @Step("Переход в \"Конструктор\" по клику на логотип \"Stellar Burgers\"")
    public void navigateToConstructor2() {
        getProfilePage().goToHomePage();
        getHomePage().waitForPurchase();
    }

    @Step("Переход в личный кабинет пользователя")
    private void goToUserProfile() {
        clickProfilePageButton();
        getProfilePage().waitForProfileText();
    }

    @Test
    @DisplayName("Выход из аккаунта")
    @Description("Выход из аккаунта")
    public void testLogout() {
        clickProfilePageButton();

        logoutUser();
    }

    @Step("Нажатие кнопки \"Выход\"")
    private void logoutUser() {
        getProfilePage().logout();
        getLoginPage().waitForLoginButton();
    }

    @After
    public void tearDown() {
        deleteDefinedUser();

        super.tearDown();
    }
}
