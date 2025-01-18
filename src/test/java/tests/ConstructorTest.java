package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

public class ConstructorTest extends BaseTest {

    @Test
    @Description("Проверка перехода в раздел 'Соусы'")
    @DisplayName("Проверка перехода в раздел 'Соусы'")
    public void testNavigateToSaucesSection() {
        getConstructorPage().selectSauces();

        getConstructorPage().waitForSaucesSelection();
    }

    @Test
    @Description("Проверка перехода в раздел 'Начинки'")
    @DisplayName("Проверка перехода в раздел 'Начинки'")
    public void testNavigateToFillingsSection() {
        getConstructorPage().selectFillings();

        getConstructorPage().waitForFillingsSelection();
    }

    @Test
    @Description("Проверка перехода в раздел 'Булки'")
    @DisplayName("Проверка перехода в раздел 'Булки'")
    public void testNavigateToBunsSection() {
        getConstructorPage().selectBuns();

        getConstructorPage().waitForBunsSelection();
    }

}

