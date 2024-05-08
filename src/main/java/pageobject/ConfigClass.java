package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.util.Objects.isNull;

public class ConfigClass {
    //Абсолютный путь к браузеру является заглушкой, так как Яндекс браузер обновился и перешел на chromium 122,
    //а yandexdriver котрый мы должны были использовать в дипломе под эту версию пока нет. Последняя версия 120
    //Это путь к бинарнику который запускается через chromedriver. Без него Яндекс браузер не работает
    private final static String YANDEX_BROWSER_PATH = System.getProperty("yandexPath");

    public static void waitingVisibilityOfElement(WebDriver driver, By element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    @Step("Создание WebDriver")
    public static WebDriver createWebDriver(WebDriver driver, String browser){
        if (browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver124.exe");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("yandex")) {
            if (isNull(YANDEX_BROWSER_PATH)){
                System.out.println("Введите путь к browser.exe у Яндекс бараузера в настройках теста. " +
                        "Для этого добавте ключ \"-DyandexPath=путь к браузеру\" при запуске теста");
            }
            System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver122.exe");
            ChromeOptions options = new ChromeOptions();
            options.setBinary(YANDEX_BROWSER_PATH);
            driver = new ChromeDriver(options);
        } else {
            System.out.println("Неподдреживаемый браузер:" + browser);
        }
        return driver;
    }
}
