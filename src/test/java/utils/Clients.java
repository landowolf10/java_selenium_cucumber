package utils;

import org.openqa.selenium.Capabilities;
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
        String os = System.getProperty("os.name");
        System.out.println("OS: " + os);

        if (browser.equalsIgnoreCase("Chrome")) {
            if (os.contains("Windows"))
                System.setProperty("chromeDriver", ConstantData.chromeDriverPathWindows);
            else if (os.contains("Linux"))
                System.setProperty("chromeDriver", ConstantData.chromeDriverPathLinux);

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setCapability("browserVersion", "114.0.5735.90");
            chromeOptions.addArguments("--remote-allow-origins=*");
            chromeOptions.addArguments("--headless");
            driver = new ChromeDriver(chromeOptions);
        }
        else if (browser.equalsIgnoreCase("Firefox")) {
            if (os.contains("Windows"))
                System.setProperty("geckoDriver", ConstantData.geckoDriverPathWindows);
            else if (os.contains("Linux"))
                System.setProperty("geckoDriver", ConstantData.geckoDriverPathLinux);

            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--headless");
            firefoxOptions.setBinary("/home/circleci/project/src/test/resources/drivers/geckodriver");
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