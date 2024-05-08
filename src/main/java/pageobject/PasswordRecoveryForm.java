package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecoveryForm {
    private final WebDriver driver;

    private final By loginLink = By.xpath(".//a[text() = 'Войти']");

    public PasswordRecoveryForm(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Нажатие на надпись-ссылку Войти")
    public void clickLoginLink(){
        driver.findElement(loginLink).click();
    }

}
