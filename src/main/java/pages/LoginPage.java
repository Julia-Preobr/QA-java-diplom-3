package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends AbstractBasePage {

    //Заголовок "Вход"
    protected final By entrance = By.xpath(".//main/div/h2[text()='Вход']");
    //Поле "Email"
    protected final By emailField = By.xpath(".//div[@class='input pr-6 pl-6 input_type_text input_size_default']/input[@name='name']");
    //Поле "Пароль"
    private final By passwordField = By.xpath(".//div[@class='input pr-6 pl-6 input_type_password input_size_default']/input[@name='Пароль' and @type='password']");
    //Кнопка "Войти"
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    //Ссылка "Зарегистрироваться"
    private final By registerLink = By.xpath(".//a[@href='/register' and text()='Зарегистрироваться']");
    //Ссылка "Восстановить пароль"
    private final By forgotPasswordLink = By.xpath(".//a[@href='/forgot-password' and text()='Восстановить пароль']");
    //Кнопка "Оформить заказ" есть только у успешно вошедшего пользователя
    private final By purchaseButton = By.xpath(".//button[text()='Оформить заказ']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Вводим пароль в поле "Пароль"
    public void enterPassword(String password) {
        sendKeys(passwordField, password);
    }

    // Нажимаем кнопку "Войти"
    public void clickLogin() {
        clickElement(loginButton);
    }

    // Регистрируем пользователя
    public void register() {
        clickElement(registerLink);
    }

    // Вводим email в поле "Email"
    public void enterEmail(String email) {
        sendKeys(emailField, email);
    }

    // Переход на страницу регистрации
    public void goToRegisterPage() {
        clickElement(registerLink);
    }

    // Переход на страницу восстановления пароля
    public void goToForgotPasswordPage() {
        clickElement(forgotPasswordLink);
    }

    // Проверить наличие кнопки "Оформить заказ"
    public void waitForPurchase() {
        waitForVisibility(purchaseButton);
    }
}
