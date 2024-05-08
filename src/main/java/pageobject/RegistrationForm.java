package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationForm {
    private final WebDriver driver;
    private final By nameInput = By.xpath(".//label[text() = 'Имя']/parent::div//input");
    private final By emailInput = By.xpath(".//label[text() = 'Email']/parent::div//input");
    private final By passwordInput = By.xpath(".//label[text() = 'Пароль']/parent::div//input");
    private final By registrationButton = By.xpath(".//button[text() = 'Зарегистрироваться']");
    private final By incorrectPasswordError = By.xpath(".//*[text() = 'Некорректный пароль']");
    private final By loginLink = By.xpath(".//a[text() = 'Войти']");

    public RegistrationForm(WebDriver driver) {
        this.driver = driver;
    }


    @Step("Ввод имени в поле Имя")
    public void setNameInput(String name){
        driver.findElement(nameInput).sendKeys(name);
    }
    @Step("Ввод почты в поле Email")
    public void setEmailInput(String email){
        driver.findElement(emailInput).sendKeys(email);
    }
    @Step("Ввод пароля в поле Password")
    public void setPasswordInput(String password){
        driver.findElement(passwordInput).sendKeys(password);
    }
    @Step("Нажатие на кнопку Зарегестрироваться")
    public void registrationButtonClick(){
        driver.findElement(registrationButton).click();
    }
    @Step("Получение текста ошибки о неверно введеном пароле")
    public String getIncorrectPasswordError(){
        return driver.findElement(incorrectPasswordError).getText();
    }
    @Step("Нажатие на надпись-ссылку Войти")
    public void clickLoginLink(){
        driver.findElement(loginLink).click();
    }

    public void setInfoAndClickRegistration(String name, String email, String password){
        setNameInput(name);
        setEmailInput(email);
        setPasswordInput(password);
        registrationButtonClick();
    }

}
