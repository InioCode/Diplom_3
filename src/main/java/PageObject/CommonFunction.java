package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonFunction {
    private final static String yandexBrowserPath = "C:\\Users\\Николай\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe";

    public static void waitingVisibilityOfElement(WebDriver driver, By element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public static WebDriver createWebDriver(WebDriver driver, String browser){
        if (browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver124.exe");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("yandex")) {
            System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver122.exe");
            ChromeOptions options = new ChromeOptions();
            options.setBinary(yandexBrowserPath);
            driver = new ChromeDriver(options);
        } else {
            System.out.println("Неподдреживаемый браузер:" + browser);
        }
        return driver;
    }
}
