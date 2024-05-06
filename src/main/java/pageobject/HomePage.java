package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private final WebDriver driver;
    private final By personalCabinet = By.xpath(".//p[text() = 'Личный Кабинет']");
    private final By loginInAccountButton = By.xpath(".//button[text() = 'Войти в аккаунт']");
    private final By createOrderButton = By.xpath((".//button[text() = 'Оформить заказ']"));
    private final By bunTabTypeButton = By.xpath(".//span[text() = 'Булки']/parent::div");
    private final By sauceTabTypeButton = By.xpath(".//span[text() = 'Соусы']/parent::div");
    private final By fillingTypeButton = By.xpath(".//span[text() = 'Начинки']/parent::div");
    private final By activeSectionLabel = By.xpath(".//div[contains(@class, 'current') and contains(@class, 'tab_type')]/span ");
    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void waitingVisibilityOfCreateOrderButton(){
        ConfigClass.waitingVisibilityOfElement(driver,createOrderButton);
    }

    public void waitingVisibilityOfPersonalCabinetButton(){
        ConfigClass.waitingVisibilityOfElement(driver, personalCabinet);
    }


    public void clickPersonalCabinet(){
        driver.findElement(personalCabinet).click();
    }

    public void clickLoginInAccountButton(){
        driver.findElement(loginInAccountButton).click();
    }
    public String getLabelCreateOrderButton(){
       return driver.findElement(createOrderButton).getText();
    }

    public void clickBunTabTypeButton(){
        driver.findElement(bunTabTypeButton).click();
    }

    public void clickSauceTabTypeButton(){
        driver.findElement(sauceTabTypeButton).click();
    }

    public void clickFillingTabTypeButton(){
        driver.findElement(fillingTypeButton).click();
    }
    public String getActiveTabLabel(){
        return driver.findElement(activeSectionLabel).getText();
    }



}
