import pageobject.HomePage;
import pageobject.LoginForm;
import pageobject.PasswordRecoveryForm;
import pageobject.RegistrationForm;
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

public class LoginTest {
    private WebDriver driver = null;
    private boolean userCreated = false;
    private String accessToken;
    private String email;
    private String password;
    private String browser;

    @Before
    public void setUp(){
        browser = "yandex";
        driver = createWebDriver(driver ,browser);
        driver.get(BASE_URL);
        driver.manage().window().maximize();

        int randInt = new Random().nextInt(1000);

        email = "text" + randInt + "@mail.ru";
        password = "Password";

        accessToken = createUserAndGetToken(email,password);
        userCreated = true;
    }

    @After
    public void tearDown(){
        driver.quit();
        if (userCreated){
            deleteUser(accessToken);
        }
    }

    @DisplayName("Открыть форму для входа и залогинится через кнопку \"Войти в аккаунт\" на главной странице")
    @Test
    public void loginInHomePage(){
        HomePage objHomePage = new HomePage(driver);
        LoginForm objLoginForm = new LoginForm(driver);

        objHomePage.clickLoginInAccountButton();
        objLoginForm.setLoginInfoAndClickButton(email, password);
        objHomePage.waitingVisibilityOfCreateOrderButton();

        Assert.assertEquals("Оформить заказ", objHomePage.getLabelCreateOrderButton());

    }

    @DisplayName("Открыть форму для входа и залогинится через кнопку \"Личный кабинет\" на главной странице")
    @Test
    public void loginThroughButtonPersonalCabinet(){
        HomePage objHomePage = new HomePage(driver);
        LoginForm objLoginForm = new LoginForm(driver);

        objHomePage.clickPersonalCabinet();
        objLoginForm.setLoginInfoAndClickButton(email, password);
        objHomePage.waitingVisibilityOfCreateOrderButton();

        Assert.assertEquals("Оформить заказ", objHomePage.getLabelCreateOrderButton());

    }

    @DisplayName("Открыть форму для входа и залогинится через кнопку \"Войти\" в форме регистрации")
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

    @DisplayName("Открыть форму для входа и залогинится через кнопку \"Войти\" в форме востановления пароля")
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

