package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalCabinet {
    private final WebDriver driver;

    public PersonalCabinet(WebDriver driver) {
        this.driver = driver;
    }
    public void waitingVisibilityOfExitButton(){
        ConfigClass.waitingVisibilityOfElement(driver, exitButton);
    }

    public void waitingVisibilityOfProfileLabel(){
        ConfigClass.waitingVisibilityOfElement(driver, profileLabel);
    }
    private final By profileLabel = By.xpath(".//*[text() = 'Профиль']");
    private final By exitButton = By.xpath(".//button[text() = 'Выход']");
    @Step("Получение подписи профиля")
    public String getProfileLabel(){
        return driver.findElement(profileLabel).getText();
    }
    @Step("Нажатие на кнопку Выйти")
    public void clickExitButton(){
        driver.findElement(exitButton).click();
    }
}
