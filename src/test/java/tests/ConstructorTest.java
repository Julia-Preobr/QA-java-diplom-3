package tests;

import io.qameta.allure.Step;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.ConstructorPage;
import web.BrowserType;

@RunWith(Parameterized.class)
public class ConstructorTest extends BaseTest {

    private ConstructorPage constructorPage;

    public ConstructorTest(BrowserType browserType) {
        super(browserType);
    }

    @Before
    public void setUp() {
        super.setUp();

        constructorPage = new ConstructorPage(driver);
    }

    @Test
    @Step("Проверка перехода в раздел 'Соусы'")
    public void testNavigateToSaucesSection() {
        constructorPage.selectSauces();

        constructorPage.waitForSaucesSelection();
    }

    @Test
    @Step("Проверка перехода в раздел 'Начинки'")
    public void testNavigateToFillingsSection() {
        constructorPage.selectFillings();

        constructorPage.waitForFillingsSelection();
    }

    @Test
    @Step("Проверка перехода в раздел 'Булки'")
    public void testNavigateToBunsSection() {
        constructorPage.selectBuns();

        constructorPage.waitForBunsSelection();
    }

}

