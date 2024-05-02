package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginForm {
    private final WebDriver driver;
    private final By emailInput = By.xpath(".//label[text() = 'Email']/parent::div//input");
    private final By passwordInput = By.xpath(".//label[text() = 'Пароль']/parent::div//input");
    private final By loginButton = By.xpath(".//button[text() = 'Войти']");
    private final By registrationLink = By.xpath(".//a[text() = 'Зарегистрироваться']");
    private final By passwordRecoveryLink = By.xpath(".//a[text() = 'Восстановить пароль']");

    public LoginForm(WebDriver driver) {
        this.driver = driver;
    }

    public void waitingVisibilityOfLoginButton(){
        CommonFunction.waitingVisibilityOfElement(driver, loginButton);
    }

    public void registrationLinkClick(){
        driver.findElement(registrationLink).click();
    }
    public void passwordRecoveryLinkClick(){
        driver.findElement(passwordRecoveryLink).click();
    }

    public void setEmailInput(String email){
        driver.findElement(emailInput).sendKeys(email);
    }

    public String getEmailInput() {
        return driver.findElement(emailInput).getText();
    }

    public void clearEmailInput(){
        driver.findElement(emailInput).clear();
    }

    public void setPasswordInput(String password){
        driver.findElement(passwordInput).sendKeys(password);
    }
    public void getPasswordInput(){
        driver.findElement(passwordInput).getText();
    }

    public void clearPasswordInput(){
        driver.findElement(passwordInput).clear();
    }

    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }

    public String getLoginButtonLabel(){
        return driver.findElement(loginButton).getText();
    }


    public void setLoginInfoAndClickButton(String email, String password){
        setEmailInput(email);
        setPasswordInput(password);
        clickLoginButton();
    }


}
