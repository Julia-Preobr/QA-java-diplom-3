package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends AbstractBasePage {

    //Кнопка "Войти"
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    //Логотип "Stellar Burgers"
    private final By logo = By.xpath(".//div/a[@href='/']");
    //Кнопка "Конструктор"
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    //Кнопка перехода "Булки"
    private final By bunsButton = By.xpath("//span[@class='text text_type_main-default'][text()='Булки']");
    //Кнопка перехода "Соуса"
    private final By saucesButton = By.xpath("//span[@class='text text_type_main-default'][text()='Соусы']");
    //Кнопка перехода "Начинки"
    private final By fillingsButton = By.xpath("//span[@class='text text_type_main-default'][text()='Начинки']");
    private final By activityTopping = By.xpath("//div[starts-with(@class,'tab_tab__1SPyG tab_tab_type_current__2BEPc')]//span");

    private final By profileText = By.xpath(".//p[text() = 'В этом разделе вы можете изменить свои персональные данные']");

    //картинка с "Булкой" для проверки видимости раздела
    public By bunsImg = By.xpath(".//img[@alt='Краторная булка N-200i']");
    //текст заголовка "Булки" для проверки видимости раздела
    public By bunsText = By.xpath(".//h2[text()='Булки']");
    //картинка с "Соусом" для проверки видимости раздела
    public By saucesImg = By.xpath(".//p[text()='Соус с шипами Антарианского плоскоходца']");
    //картинка с "Начинкой" для проверки видимости раздела
    public By fillingsImg = By.xpath(".//img[@alt='Плоды Фалленианского дерева']");
    //текст для проверки видимости на главной странице
    public By textBurgerMainPage = By.xpath(".//section/h1[text()='Соберите бургер']");


    private By logoutButton = By.id("logout");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    // Логика выхода из аккаунта
    public void logout() {
        clickElement(logoutButton);
    }

    // Проверка наличия кнопки "Войти в аккаунт"
    public boolean isLoginButtonVisible() {
        return isElementPresent(loginButton);
    }

    // Проверка наличия кнопки "Личный кабинет"
    public boolean isAccountButtonVisible() {
        return isElementPresent(accountButton);
    }// Переход на страницу профиля (Личный кабинет)

    public void goToAccountPage() {
        clickElement(accountButton);
    }

    // Проверка наличия логотипа "Stellar Burgers"
    public boolean isLogoVisible() {
        return isElementPresent(logo);
    }

    // Переход в конструктор
    public void goToConstructor() {
        clickElement(constructorButton);
    }

    public void waitForProfileText() {
        waitForVisibility(profileText);
    }

    // Переход на раздел "Булки"
    public void goToBunsSection() {
        clickElement(bunsButton);
    }

    // Переход на раздел "Соуса"
    public void goToSaucesSection() {
        clickElement(saucesButton);
    }

    // Переход на раздел "Начинки"
    public void goToFillingsSection() {
        clickElement(fillingsButton);
    }

    // Проверка видимости раздела "Булки"
    public boolean isBunsSectionVisible() {
        return isElementPresent(bunsImg) && isElementPresent(bunsText);
    }

    // Проверка видимости раздела "Соусы"
    public boolean isSaucesSectionVisible() {
        return isElementPresent(saucesImg);
    }

    // Проверка видимости раздела "Начинки"
    public boolean isFillingsSectionVisible() {
        return isElementPresent(fillingsImg);
    }

    // Проверка видимости текста на главной странице
    public boolean isMainPageTextVisible() {
        return isElementPresent(textBurgerMainPage);
    }

    // Проверка, что выбран активный элемент в разделе
    public boolean isActivityToppingActive() {
        return isElementPresent(activityTopping);
    }

    // Вспомогательный метод для проверки наличия элемента
    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}