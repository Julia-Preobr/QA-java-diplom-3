package tests;

import data.User;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import web.BrowserType;

@RunWith(Parameterized.class)
public class TransitionsTest extends BaseTest {
    protected static User testUser;

    public TransitionsTest(BrowserType browserType) {
        super(browserType);
    }

    @Before
    public void setUp() {
        super.setUp();

        getHomePage().goToHomePage();

        testUser = getRandomUser();
        createDefinedUser(testUser);

        tryUserLogin(testUser);
        checkIfHomePageHasPurchase();
    }

    @Test
    @Step("Проверка перехода в раздел 'Соусы'")
    public void testNavigateToSaucesSection() {
        clickProfilePageButton();

        getProfilePage().waitForProfileText();
    }

    @After
    public void tearDown() {
        deleteActiveUser(testUser);

        super.tearDown();
    }
}

