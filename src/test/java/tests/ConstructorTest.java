package tests;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
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
    @Step("Проверка перехода в раздел 'Булки'")
    public void testNavigateToBunsSection() {
        constructorPage.goToBunsSection();
        String sectionTitle = driver.getTitle();
        Assert.assertEquals("Раздел 'Булки'", sectionTitle);
    }

    @Test
    @Step("Проверка перехода в раздел 'Соусы'")
    public void testNavigateToSaucesSection() {
        constructorPage.goToSaucesSection();
        String sectionTitle = driver.getTitle();
        Assert.assertEquals("Раздел 'Соусы'", sectionTitle);
    }

    @Test
    @Step("Проверка перехода в раздел 'Начинки'")
    public void testNavigateToFillingsSection() {
        constructorPage.goToFillingsSection();
        String sectionTitle = driver.getTitle();
        Assert.assertEquals("Раздел 'Начинки'", sectionTitle);
    }

    @Test
    @Step("Проверка перехода по клику на логотип Stellar Burgers")
    public void testClickOnLogo() {
        constructorPage.clickElement(By.id("logo"));
        String pageTitle = driver.getTitle();
        Assert.assertEquals("Stellar Burgers", pageTitle);
    }
}

