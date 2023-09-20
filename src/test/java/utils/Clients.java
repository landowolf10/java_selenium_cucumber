package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utils.ConstantData.chromeInstanceExists;

public class Clients
{
    WebDriver driver;
    public static WebDriverWait wait;
    public static String browser;

    public WebDriver getDriver(String browser)
    {


        if (chromeInstanceExists) {
            driver = ConstantData.chromeDriverInstance;
        }
        else {
            driver = createDriver(browser);
        }

        chromeInstanceExists = true;
        ConstantData.chromeDriverInstance = driver;
        Clients.browser = browser;

        System.out.println("Browser: " + Clients.browser);

        return driver;
    }

    private WebDriver createDriver(String browser)
    {
        if (browser.equalsIgnoreCase("Chrome")) {
            System.setProperty("chromeDriver", ConstantData.chromeDriverPath);
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setCapability("browserVersion", "114.0.5735.90");
            chromeOptions.addArguments("--remote-allow-origins=*");
            chromeOptions.addArguments("--headless");
            driver = new ChromeDriver(chromeOptions);
        }
        else if (browser.equalsIgnoreCase("Firefox")) {
            System.setProperty("geckoDriver", ConstantData.geckoDriverPath);
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--headless");
            driver = new FirefoxDriver(firefoxOptions);
        }

        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        return driver;
    }

    public static void quitDriver() {
        WebDriver currentChromeDriver;

        if (chromeInstanceExists)
        {
            currentChromeDriver = ConstantData.chromeDriverInstance;
            currentChromeDriver.quit();
        }

        chromeInstanceExists = false;
        ConstantData.chromeDriverInstance = null;
    }
}