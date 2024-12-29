package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends AbstractBasePage {

    // Ссылка "Войти"
    private final By enterLink = By.xpath(".//div/a[@href='/login' and text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    // Вход
    public void enter() {
        clickElement(enterLink);
    }

}
