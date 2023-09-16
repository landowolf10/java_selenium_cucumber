package utils;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class TakeScreenShot extends BasePage{
    public TakeScreenShot(String browser) {
        super(browser);
    }

    public static void takeScreenShot(Scenario scenario)
    {
        //BasePage basePage = new BasePage(Clients.browser);

        if (scenario.isFailed())
        {
            final byte[] screenshot = ((TakesScreenshot) chromeDriver).getScreenshotAs(OutputType.BYTES);

            scenario.attach(screenshot, "image/png", "Screenshot");
        }
    }
}
