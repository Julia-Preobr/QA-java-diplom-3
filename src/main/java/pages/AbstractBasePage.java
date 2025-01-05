package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class AbstractBasePage {
    protected WebDriver driver;
    protected Actions actions;

    protected WebDriverWait wait;

    //Логотип "Stellar Burgers"
    protected final By enterButton = By.xpath(".//div/a[@href='/']");
    //Кнопка "Конструктор"
    protected final By constructorButton = By.xpath(".//a/p[text()='Конструктор']");
    //Кнопка "Личный кабинет"
    protected final By accountButton = By.xpath(".//a[@href='/account']");

    public AbstractBasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    public void clickElement(By locator) {
        actions.moveToElement(findElement(locator)).click().perform();
    }

    public void sendKeys(By locator, String text) {
        findElement(locator).sendKeys(text);
    }

    public String getText(By locator) {
        return findElement(locator).getText();
    }

    public void waitForVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
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
