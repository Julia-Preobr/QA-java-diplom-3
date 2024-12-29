package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConstructorPage extends AbstractBasePage {

    private By bunsSection = By.id("buns");
    private By saucesSection = By.id("sauces");
    private By fillingsSection = By.id("fillings");

    public ConstructorPage(WebDriver driver) {
        super(driver);
    }

    public void goToBunsSection() {
        clickElement(bunsSection);
    }

    public void goToSaucesSection() {
        clickElement(saucesSection);
    }

    public void goToFillingsSection() {
        clickElement(fillingsSection);
    }
}
