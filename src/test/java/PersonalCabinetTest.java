import PageObject.HomePage;
import PageObject.LoginForm;
import PageObject.PersonalCabinet;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static PageObject.CommonFunction.createWebDriver;

public class PersonalCabinetTest {
    public static final String URL = "https://stellarburgers.nomoreparties.site";
    private WebDriver driver = null;

    private String email;
    private String password;


    @Before
    public void setUp(){
        driver = createWebDriver(driver,"chrome");
        driver.get(URL);
        driver.manage().window().maximize();

        email = "text456@mail.ru";
        password = "Password";
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @DisplayName("Войти в личный кабинет")
    @Test
    public void openPersonalCabinet(){
        HomePage objHomePage = new HomePage(driver);
        LoginForm objLoginForm = new LoginForm(driver);
        PersonalCabinet objPersonalCabinet = new PersonalCabinet(driver);

        objHomePage.clickLoginInAccountButton();
        objLoginForm.setLoginInfoAndClickButton(email, password);
        objHomePage.waitingVisibilityOfPersonalCabinetButton();
        objHomePage.clickPersonalCabinet();
        objPersonalCabinet.waitingVisibilityOfProfileLabel();

       Assert.assertEquals("Профиль", objPersonalCabinet.getProfileLabel());
    }

    @DisplayName("Выйти из личный кабинета")
    @Test
    public void exitAccount(){
        HomePage objHomePage = new HomePage(driver);
        LoginForm objLoginForm = new LoginForm(driver);
        PersonalCabinet objPersonalCabinet = new PersonalCabinet(driver);

        objHomePage.clickLoginInAccountButton();
        objLoginForm.setLoginInfoAndClickButton(email, password);

        objHomePage.waitingVisibilityOfPersonalCabinetButton();
        objHomePage.clickPersonalCabinet();

        objPersonalCabinet.waitingVisibilityOfExitButton();
        objPersonalCabinet.clickExitButton();

        objLoginForm.waitingVisibilityOfLoginButton();
        Assert.assertEquals("Войти", objLoginForm.getLoginButtonLabel());

    }
}
