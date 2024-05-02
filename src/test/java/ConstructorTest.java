import PageObject.HomePage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import static PageObject.CommonFunction.createWebDriver;

@RunWith(Parameterized.class)
public class ConstructorTest {
    public static final String URL = "https://stellarburgers.nomoreparties.site";
    private WebDriver driver = null;

    private final String browser;

    public ConstructorTest(String browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Object[][] getProperty(){
        return new Object[][]{
                {"yandex"},
                {"chrome"}
        };
    }

    @Before
    public void setUp(){
        driver = createWebDriver(driver ,browser);
        driver.get(URL);
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
        objHomePage.waitingVisibilityOfFillingSectionLabel();

        objHomePage.clickBunTabTypeButton();
        objHomePage.waitingVisibilityOfBunSectionLabel();
        Assert.assertEquals("Булки", objHomePage.getLabelBunSection());
    }

    @DisplayName("Клик по вкладке Соусы прокуручивает конструктор до соответсвующего раздела")
    @Test
    public void sauceTabTypeButtonScrollToSauces(){
        HomePage objHomePage = new HomePage(driver);

        objHomePage.clickSauceTabTypeButton();
        objHomePage.waitingVisibilityOfSauceSectionLabel();
        Assert.assertEquals("Соусы", objHomePage.getLabelSauceSection());
    }

    @DisplayName("Клик по вкладке Начинки прокуручивает конструктор до соответсвующего раздела")
    @Test
    public void fillingTabTypeButtonScrollToFillings(){
        HomePage objHomePage = new HomePage(driver);

        objHomePage.clickFillingTabTypeButton();
        objHomePage.waitingVisibilityOfFillingSectionLabel();
        Assert.assertEquals("Начинки", objHomePage.getLabelFillingSection());
    }
}
