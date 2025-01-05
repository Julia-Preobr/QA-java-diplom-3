package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends AbstractBasePage {

    // Текст заголовка "Регистрация" для проверки перехода на страницу регистрации
    public final By registerText = By.xpath(".//div/h2[text()='Регистрация']");
    // Поле "Имя"
    private final By nameField = By.xpath(".//div[./label[text()='Имя']]/input[@name='name']");
    // Поле "Email"
    private final By emailField = By.xpath(".//div[./label[text()='Email']]/input[@name='name']");
    // Поле "Пароль"
    private final By passwordField = By.xpath(".//div[./label[text()='Пароль']]/input[@name='Пароль']");
    // Кнопка "Зарегистрироваться"
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    // Текст "Некорректный пароль"
    public final By errorPasswordText = By.xpath(".//p[text()='Некорректный пароль']");
    // Ссылка "Войти"
    private final By enterLink = By.xpath(".//p/a[@href='/login' and text()='Войти']");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void enterName(String name) {
        sendKeys(nameField, name);
    }

    public void enterEmail(String email) {
        sendKeys(emailField, email);
    }

    public void enterPassword(String password) {
        sendKeys(passwordField, password);
    }

    public void clickRegister() {
        clickElement(registerButton);
    }

    public void waitForIncorrectPasswordEntered() {
        waitForVisibility(errorPasswordText);
    }

    public void enter() {
        clickElement(enterLink);
    }
}
