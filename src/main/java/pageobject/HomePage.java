package pageobject;

import io.qameta.allure.Step;
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
    @Step("Ожидание видимости кнопки Создать заказ")
    public void waitingVisibilityOfCreateOrderButton(){
        ConfigClass.waitingVisibilityOfElement(driver,createOrderButton);
    }
    @Step("Ожидание видимости кнопки Личный Кабинет")
    public void waitingVisibilityOfPersonalCabinetButton(){
        ConfigClass.waitingVisibilityOfElement(driver, personalCabinet);
    }
    @Step("Нажатие кнопки Личный Кабинет")
    public void clickPersonalCabinet(){
        driver.findElement(personalCabinet).click();
    }
    @Step("Нажатие кнопки Войти в аккаунт")
    public void clickLoginInAccountButton(){
        driver.findElement(loginInAccountButton).click();
    }
    @Step("Нажатие кнопки Создать заказ")
    public String getLabelCreateOrderButton(){
       return driver.findElement(createOrderButton).getText();
    }
    @Step("Нажатие кнопки раздела Булки в конструкторе")
    public void clickBunTabTypeButton(){
        driver.findElement(bunTabTypeButton).click();
    }
    @Step("Нажатие кнопки раздела Соусы в конструкторе")
    public void clickSauceTabTypeButton(){
        driver.findElement(sauceTabTypeButton).click();
    }
    @Step("Нажатие кнопки раздела Начинки в конструкторе")
    public void clickFillingTabTypeButton(){
        driver.findElement(fillingTypeButton).click();
    }
    @Step("Получение названия активног раздела")
    public String getActiveTabLabel(){
        return driver.findElement(activeSectionLabel).getText();
    }



}
