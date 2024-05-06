import pageobject.HomePage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static api.UrlConstants.BASE_URL;
import static pageobject.ConfigClass.createWebDriver;

public class ConstructorTest {
    private WebDriver driver = null;
    private String browser;

    @Before
    public void setUp(){
        browser = "yandex";
        driver = createWebDriver(driver ,browser);
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @DisplayName("Клик по вкладке Булки прокуручивает конструктор до соответсвующего раздела")
    @Test
    public void bunTabTypeButtonScrollToBuns(){
        HomePage objHomePage = new HomePage(driver);
        objHomePage.clickFillingTabTypeButton();

        objHomePage.clickBunTabTypeButton();
        Assert.assertEquals("Булки", objHomePage.getActiveTabLabel());
    }

    @DisplayName("Клик по вкладке Соусы прокуручивает конструктор до соответсвующего раздела")
    @Test
    public void sauceTabTypeButtonScrollToSauces(){
        HomePage objHomePage = new HomePage(driver);

        objHomePage.clickSauceTabTypeButton();
        Assert.assertEquals("Соусы", objHomePage.getActiveTabLabel());
    }

    @DisplayName("Клик по вкладке Начинки прокуручивает конструктор до соответсвующего раздела")
    @Test
    public void fillingTabTypeButtonScrollToFillings(){
        HomePage objHomePage = new HomePage(driver);

        objHomePage.clickFillingTabTypeButton();
        Assert.assertEquals("Начинки", objHomePage.getActiveTabLabel());
    }
}
