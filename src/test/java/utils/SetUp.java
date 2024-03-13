package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SetUp
{
    WebDriver driver;
    public static WebDriverWait wait;
    public static String browser;
    public static boolean driverInstanceExists = false;
    public static WebDriver driverInstance = null;

    public WebDriver getDriver(String browser)
    {
        if (driverInstanceExists) {
            driver = driverInstance;
        }
        else {
            driver = createDriver(browser);
        }

        driverInstanceExists = true;
        driverInstance = driver;
        SetUp.browser = browser;

        System.out.println("Browser: " + SetUp.browser);

        return driver;
    }

    private WebDriver createDriver(String browser)
    {
        String os = System.getProperty("os.name");
        System.out.println("OS: " + os);

        if (browser.equalsIgnoreCase("Chrome")) {
            if (os.contains("Windows"))
                System.setProperty("webdriver.chrome.driver", ConstantData.chromeDriverPathWindows);
            else if (os.contains("Linux"))
                System.setProperty("webdriver.chrome.driver", ConstantData.chromeDriverPathLinux);

            ChromeOptions chromeOptions = new ChromeOptions();
            //chromeOptions.setCapability("browserVersion", "114.0.5735.90");
            chromeOptions.addArguments("--remote-allow-origins=*");
            chromeOptions.addArguments("--headless");
            driver = new ChromeDriver(chromeOptions);
        }
        else if (browser.equalsIgnoreCase("Firefox")) {
            if (os.contains("Windows"))
                System.setProperty("webdriver.gecko.driver", ConstantData.geckoDriverPathWindows);
            else if (os.contains("Linux"))
                System.setProperty("webdriver.gecko.driver", ConstantData.geckoDriverPathLinux);

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

        if (driverInstanceExists)
        {
            currentChromeDriver = driverInstance;
            currentChromeDriver.quit();
        }

        driverInstanceExists = false;
        driverInstance = null;
    }
}