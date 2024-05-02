import PageObject.HomePage;
import PageObject.LoginForm;
import PageObject.PasswordRecoveryForm;
import PageObject.RegistrationForm;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static PageObject.CommonFunction.createWebDriver;

public class LoginTest {
    public static final String URL = "https://stellarburgers.nomoreparties.site";
    private WebDriver driver = null;

    private String email;
    private String password;



    @Before
    public void setUp(){
        driver = createWebDriver(driver ,"chrome");
        driver.get(URL);
        driver.manage().window().maximize();

        email = "text456@mail.ru";
        password = "Password";
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void loginInHomePage(){
        HomePage objHomePage = new HomePage(driver);
        LoginForm objLoginForm = new LoginForm(driver);

        objHomePage.clickLoginInAccountButton();
        objLoginForm.setLoginInfoAndClickButton(email, password);
        objHomePage.waitingVisibilityOfCreateOrderButton();

        Assert.assertEquals("Оформить заказ", objHomePage.getLabelCreateOrderButton());

    }

    @Test
    public void loginThroughButtonPersonalCabinet(){
        HomePage objHomePage = new HomePage(driver);
        LoginForm objLoginForm = new LoginForm(driver);

        objHomePage.clickPersonalCabinet();
        objLoginForm.setLoginInfoAndClickButton(email, password);
        objHomePage.waitingVisibilityOfCreateOrderButton();

        Assert.assertEquals("Оформить заказ", objHomePage.getLabelCreateOrderButton());

    }

    @Test
    public void loginThroughRegistrationForm(){
        HomePage objHomePage = new HomePage(driver);
        LoginForm objLoginForm = new LoginForm(driver);
        RegistrationForm objRegistrationForm = new RegistrationForm(driver);

        objHomePage.clickPersonalCabinet();
        objLoginForm.registrationLinkClick();
        objRegistrationForm.clickLoginLink();
        objLoginForm.setLoginInfoAndClickButton(email, password);
        objHomePage.waitingVisibilityOfCreateOrderButton();

        Assert.assertEquals("Оформить заказ", objHomePage.getLabelCreateOrderButton());

    }

    @Test
    public void loginThroughPasswordRecoveryForm(){
        HomePage objHomePage = new HomePage(driver);
        LoginForm objLoginForm = new LoginForm(driver);
        PasswordRecoveryForm objPasswordRecoveryForm = new PasswordRecoveryForm(driver);

        objHomePage.clickPersonalCabinet();
        objLoginForm.passwordRecoveryLinkClick();
        objPasswordRecoveryForm.clickLoginLink();
        objLoginForm.setLoginInfoAndClickButton(email, password);
        objHomePage.waitingVisibilityOfCreateOrderButton();

        Assert.assertEquals("Оформить заказ", objHomePage.getLabelCreateOrderButton());
    }
}

