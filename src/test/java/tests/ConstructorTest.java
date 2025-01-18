package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;

public class ConstructorTest extends BaseTest {

    @Test
    @Description("Проверка перехода в раздел 'Соусы'")
    @DisplayName("Проверка перехода в раздел 'Соусы'")
    public void testNavigateToSaucesSection() {
        getConstructorPage().selectSauces();

        getConstructorPage().waitForSaucesSelection();

        Assert.assertTrue("Раздел \"Соусы\" невидима", getConstructorPage().isSaucesSectionVisible());
    }

    @Test
    @Description("Проверка перехода в раздел 'Начинки'")
    @DisplayName("Проверка перехода в раздел 'Начинки'")
    public void testNavigateToFillingsSection() {
        getConstructorPage().selectFillings();

        getConstructorPage().waitForFillingsSelection();

        Assert.assertTrue("Раздел \"Начинки\" невидима", getConstructorPage().isFillingsSectionVisible());
    }

    @Test
    @Description("Проверка перехода в раздел 'Булки'")
    @DisplayName("Проверка перехода в раздел 'Булки'")
    public void testNavigateToBunsSection() {
        getConstructorPage().selectBuns();

        getConstructorPage().waitForBunsSelection();

        Assert.assertTrue("Раздел \"Булки\" невидима", getConstructorPage().isBunsSectionVisible());
    }

}

