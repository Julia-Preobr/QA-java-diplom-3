package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends AbstractBasePage {

    //Кнопка "Войти в аккаунт"
    private final By loginToAccountButton = By.xpath(".//button[text()='Войти в аккаунт']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Нажимаем кнопку "Войти в аккаунт"
    public void clickToAccountLogin() {
        clickElement(loginToAccountButton);
    }

}
