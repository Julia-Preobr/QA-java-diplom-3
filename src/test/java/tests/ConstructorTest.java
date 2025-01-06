package tests;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import web.BrowserType;

@RunWith(Parameterized.class)
public class ConstructorTest extends BaseTest {

    public ConstructorTest(BrowserType browserType) {
        super(browserType);
    }

    @Test
    @Step("Проверка перехода в раздел 'Соусы'")
    @DisplayName("Проверка перехода в раздел 'Соусы'")
    public void testNavigateToSaucesSection() {
        getConstructorPage().selectSauces();

        getConstructorPage().waitForSaucesSelection();
    }

    @Test
    @Step("Проверка перехода в раздел 'Начинки'")
    @DisplayName("Проверка перехода в раздел 'Начинки'")
    public void testNavigateToFillingsSection() {
        getConstructorPage().selectFillings();

        getConstructorPage().waitForFillingsSelection();
    }

    @Test
    @Step("Проверка перехода в раздел 'Булки'")
    @DisplayName("Проверка перехода в раздел 'Булки'")
    public void testNavigateToBunsSection() {
        getConstructorPage().selectBuns();

        getConstructorPage().waitForBunsSelection();
    }

}

