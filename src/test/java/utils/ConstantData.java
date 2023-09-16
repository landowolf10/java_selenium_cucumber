package utils;

import org.openqa.selenium.WebDriver;

public class ConstantData {
    public static WebDriver chromeDriverInstance = null;
    public static String chromeDriverPath = "./src/test/resources/drivers/chromedriver.exe";
    public static String geckoDriverPath = "./src/test/resources/drivers/geckodriver";
    public static String URL = "https://www.saucedemo.com";
    public static boolean chromeInstanceExists = false;
}