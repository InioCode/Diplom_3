package tests;

import pageobject.HomePage;
import pageobject.LoginForm;
import pageobject.PersonalCabinet;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.Random;

import static api.CreateUser.createUserAndGetToken;
import static api.DeleteUser.deleteUser;
import static api.UrlConstants.BASE_URL;
import static pageobject.ConfigClass.createWebDriver;

public class PersonalCabinetTest {
    private WebDriver driver = null;

    private String email;
    private String password;
    private String browser;
    private String accessToken;


    @Before
    public void setUp(){
        browser = System.getProperty("browser");
        driver = createWebDriver(driver,browser);
        driver.get(BASE_URL);
        driver.manage().window().maximize();

        int randInt = new Random().nextInt(1000);
        email = "text" + randInt + "@mail.ru";
        password = "Password";

        accessToken = createUserAndGetToken(email, password);
    }

    @After
    public void tearDown(){
        driver.quit();
        deleteUser(accessToken);
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
