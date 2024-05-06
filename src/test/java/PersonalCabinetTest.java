import PageObject.HomePage;
import PageObject.LoginForm;
import PageObject.PersonalCabinet;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.Random;

import static Api.CreateUser.createUserAndGetToken;
import static Api.DeleteUser.deleteUser;
import static Api.UrlConstants.BASE_URL;
import static PageObject.ConfigClass.createWebDriver;

public class PersonalCabinetTest {
    private WebDriver driver = null;

    private String email;
    private String password;
    private String browser;
    private String accessToken;


    @Before
    public void setUp(){
        browser = "yandex";
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
