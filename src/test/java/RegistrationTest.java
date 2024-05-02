import PageObject.HomePage;
import PageObject.LoginForm;
import PageObject.RegistrationForm;
import io.qameta.allure.junit4.DisplayName;
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
public class RegistrationTest {
    public static final String URL = "https://stellarburgers.nomoreparties.site";
    private WebDriver driver = null;

    private String name;
    private String email;
    private String password;
    private String incorrectPassword;

    private final String browser;

    public RegistrationTest(String browser) {
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
        driver = createWebDriver(driver, browser);
        driver.get(URL);
        driver.manage().window().maximize();

        name = "Ivan";
        email = "text" + new Random().nextInt(1000) +"@mail.ru";
        password = "Password";
        incorrectPassword = "1234";

        //System.out.println(email);
    }

    @After
    public void tearDown(){
        driver.quit();
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
