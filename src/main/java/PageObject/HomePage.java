package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private final WebDriver driver;
    private final By personalCabinet = By.xpath(".//p[text() = 'Личный Кабинет']");
    private final By loginInAccountButton = By.xpath(".//button[text() = 'Войти в аккаунт']");
    private final By createOrderButton = By.xpath((".//button[text() = 'Оформить заказ']"));
    private final By bunTabTypeButton = By.xpath(".//span[text() = 'Булки']/parent::div");
    private final By bunSectionLabel = By.xpath(".//div[contains(@class, 'menuContainer')]/*[text() = 'Булки']");
    private final By sauceTabTypeButton = By.xpath(".//span[text() = 'Соусы']/parent::div");
    private final By sauceSectionLabel = By.xpath(".//div[contains(@class, 'menuContainer')]/*[text() = 'Соусы']");
    private final By fillingTypeButton = By.xpath(".//span[text() = 'Начинки']/parent::div");
    private final By fillingSectionLabel = By.xpath(".//div[contains(@class, 'menuContainer')]/*[text() = 'Начинки']");
    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void waitingVisibilityOfCreateOrderButton(){
        CommonFunction.waitingVisibilityOfElement(driver,createOrderButton);
    }

    public void waitingVisibilityOfPersonalCabinetButton(){
        CommonFunction.waitingVisibilityOfElement(driver, personalCabinet);
    }

    public void waitingVisibilityOfBunSectionLabel(){
        CommonFunction.waitingVisibilityOfElement(driver, bunSectionLabel);
    }
    public void waitingVisibilityOfSauceSectionLabel(){
        CommonFunction.waitingVisibilityOfElement(driver, sauceSectionLabel);
    }
    public void waitingVisibilityOfFillingSectionLabel(){
        CommonFunction.waitingVisibilityOfElement(driver, fillingSectionLabel);
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

    public String getLabelBunSection(){
        return driver.findElement(bunSectionLabel).getText();
    }

    public void clickSauceTabTypeButton(){
        driver.findElement(sauceTabTypeButton).click();
    }

    public String getLabelSauceSection(){
        return driver.findElement(sauceSectionLabel).getText();
    }

    public void clickFillingTabTypeButton(){
        driver.findElement(fillingTypeButton).click();
    }
    public String getLabelFillingSection(){
        return driver.findElement(fillingSectionLabel).getText();
    }



}
