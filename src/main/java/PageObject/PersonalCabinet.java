package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalCabinet {
    private final WebDriver driver;

    public PersonalCabinet(WebDriver driver) {
        this.driver = driver;
    }
    public void waitingVisibilityOfExitButton(){
        CommonFunction.waitingVisibilityOfElement(driver, exitButton);
    }

    public void waitingVisibilityOfProfileLabel(){
        CommonFunction.waitingVisibilityOfElement(driver, profileLabel);
    }
    private final By profileLabel = By.xpath(".//*[text() = 'Профиль']");
    private final By exitButton = By.xpath(".//button[text() = 'Выход']");

    public String getProfileLabel(){
        return driver.findElement(profileLabel).getText();
    }
    public void clickExitButton(){
        driver.findElement(exitButton).click();
    }
}
