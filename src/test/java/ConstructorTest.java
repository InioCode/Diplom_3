import PageObject.HomePage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static PageObject.CommonFunction.createWebDriver;

public class ConstructorTest {
    public static final String URL = "https://stellarburgers.nomoreparties.site";
    private WebDriver driver = null;
    @Before
    public void setUp(){
        driver = createWebDriver(driver ,"yandex");
        driver.get(URL);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void bunTabTypeButtonScrollToBuns(){
        HomePage objHomePage = new HomePage(driver);
        objHomePage.clickFillingTabTypeButton();
        objHomePage.waitingVisibilityOfFillingSectionLabel();

        objHomePage.clickBunTabTypeButton();
        objHomePage.waitingVisibilityOfBunSectionLabel();
        Assert.assertEquals("Булки", objHomePage.getLabelBunSection());
    }

    @Test
    public void sauceTabTypeButtonScrollToSauces(){
        HomePage objHomePage = new HomePage(driver);

        objHomePage.clickSauceTabTypeButton();
        objHomePage.waitingVisibilityOfSauceSectionLabel();
        Assert.assertEquals("Соусы", objHomePage.getLabelSauceSection());
    }

    @Test
    public void fillingTabTypeButtonScrollToFillings(){
        HomePage objHomePage = new HomePage(driver);

        objHomePage.clickFillingTabTypeButton();
        objHomePage.waitingVisibilityOfFillingSectionLabel();
        Assert.assertEquals("Начинки", objHomePage.getLabelFillingSection());
    }
}
