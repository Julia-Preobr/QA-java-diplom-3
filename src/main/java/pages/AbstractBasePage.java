package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class AbstractBasePage {
    protected WebDriver driver;

    //Логотип "Stellar Burgers"
    protected final By enterButton = By.xpath(".//div/a[@href='/']");
    //Кнопка "Конструктор"
    protected final By constructorButton = By.xpath(".//a/p[text()='Конструктор']");
    //Кнопка "Личный кабинет"
    protected final By accountButton = By.xpath(".//a[@href='/account']");

    public AbstractBasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    public void clickElement(By locator) {
        findElement(locator).click();
    }

    public void sendKeys(By locator, String text) {
        findElement(locator).sendKeys(text);
    }

    public String getText(By locator) {
        return findElement(locator).getText();
    }

    // Переход на главную страницу (логотип Stellar Burgers)
    public void goToHomePage() {
        clickElement(enterButton);
    }

    // Переход в конструктор
    public void goToConstructor() {
        clickElement(constructorButton);
    }

    // Переход в личный кабинет
    public void goToAccountPage() {
        clickElement(accountButton);
    }
}
