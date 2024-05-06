import Api.JsonObjects.LoginUserBodyData;
import Api.JsonObjects.SuccessLoginUserData;
import PageObject.HomePage;
import PageObject.LoginForm;
import PageObject.RegistrationForm;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.Random;

import static Api.DeleteUser.deleteUser;
import static Api.LoginUser.loginUserAndGetToken;
import static Api.UrlConstants.BASE_URL;
import static PageObject.ConfigClass.createWebDriver;

public class RegistrationTest {
    private WebDriver driver = null;
    private boolean userCreated = false;

    private String name;
    private String email;
    private String password;
    private String incorrectPassword;
    private String browser;

    @Before
    public void setUp(){
        browser = "yandex";
        driver = createWebDriver(driver, browser);
        driver.get(BASE_URL);
        driver.manage().window().maximize();

        name = "Ivan";
        email = "text" + new Random().nextInt(1000) +"@mail.ru";
        password = "Password";
        incorrectPassword = "1234";

        //System.out.println(email);
    }

    @After
    public void tearDown() throws NullPointerException{
        driver.quit();
        if (userCreated){
            String accessToken = loginUserAndGetToken(email, password);
            deleteUser(accessToken);
        }

    }

    @DisplayName("Создание новго пользоваетля в форме регистрации")
    @Test
    public void successRegistration(){
        HomePage objHomePage = new HomePage(driver);
        LoginForm objLoginForm = new LoginForm(driver);
        RegistrationForm objRegistrationForm = new RegistrationForm(driver);

        objHomePage.clickPersonalCabinet();
        objLoginForm.registrationLinkClick();
        objRegistrationForm.setInfoAndClickRegistration(name, email, password);
        userCreated = true;

        objLoginForm.waitingVisibilityOfLoginButton();

        Assert.assertEquals("Войти", objLoginForm.getLoginButtonLabel());
    }

    @DisplayName("При создании новго пользоваетля с паролем меньше 6 символов появляется ошибка")
    @Test
    public void registrationWithIncorrectPasswordReturnError(){
        HomePage objHomePage = new HomePage(driver);
        LoginForm objLoginForm = new LoginForm(driver);
        RegistrationForm objRegistrationForm = new RegistrationForm(driver);

        objHomePage.clickPersonalCabinet();
        objLoginForm.registrationLinkClick();
        objRegistrationForm.setInfoAndClickRegistration(name, email, incorrectPassword);

        Assert.assertEquals("Некорректный пароль",  objRegistrationForm.getIncorrectPasswordError());
    }

}
