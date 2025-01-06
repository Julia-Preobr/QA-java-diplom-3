package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConstructorPage extends AbstractBasePage {

    private By bunsSection = By.xpath(".//div/span[text() = 'Булки']");
    private By saucesSection = By.xpath(".//div/span[text() = 'Соусы']");
    private By fillingsSection = By.xpath(".//div/span[text() = 'Начинки']");

    private By selectedBunsSection = By.xpath(".//div[contains(@class, 'tab_tab_type_current')]/span[text() = 'Булки']");
    private By selectedSaucesSection = By.xpath(".//div[contains(@class, 'tab_tab_type_current')]/span[text() = 'Соусы']");
    private By selectedFillingsSection = By.xpath(".//div[contains(@class, 'tab_tab_type_current')]/span[text() = 'Начинки']");

    public ConstructorPage(WebDriver driver) {
        super(driver);
    }

    public void selectBuns() {
        displayAndClickElement(bunsSection);
    }

    public void selectSauces() {
        displayAndClickElement(saucesSection);
    }

    public void selectFillings() {
        displayAndClickElement(fillingsSection);
    }

    public void waitForBunsSelection() {
        waitForVisibility(selectedBunsSection);
    }

    public void waitForSaucesSelection() {
        waitForVisibility(selectedSaucesSection);
    }

    public void waitForFillingsSelection() {
        waitForVisibility(selectedFillingsSection);
    }

}
