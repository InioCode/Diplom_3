import JsonObjects.SuccessRegisterUserData;
import PageObject.HomePage;
import PageObject.LoginForm;
import PageObject.PasswordRecoveryForm;
import PageObject.RegistrationForm;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import java.util.Random;

import static PageObject.CommonFunction.createWebDriver;

@RunWith(Parameterized.class)
public class LoginTest {
    public static final String URL = "https://stellarburgers.nomoreparties.site";
    private WebDriver driver = null;
    private String accessToken;
    private String email;
    private String password;

    private final String browser;

    public LoginTest(String browser) {
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

        int randInt = new Random().nextInt(1000);

        email = "text" + randInt + "@mail.ru";
        password = "Password";
        //System.out.println(email);

        String bodyCreateUser = "{\"email\": \"" + email + "\",\"password\": \"" + password + "\",\"name\": \"Username\"}";

        RestAssured.baseURI = URL;
        Response response = RestAssured
                .given().log().all()
                .header("Content-type", "application/json" )
                .body(bodyCreateUser)
                .and()
                .post("/api/auth/register");

        accessToken = response.as(SuccessRegisterUserData.class).getAccessToken().substring(7);
    }

    @After
    public void tearDown(){

        RestAssured.given()
                .auth()
                .oauth2(accessToken)
                .delete("https://stellarburgers.nomoreparties.site/api/auth/user");

        driver.quit();
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

