package steps;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import pages.LoginPage;
import utils.SetUp;

import static utils.TakeScreenShot.takeScreenShot;

public class CommonSteps {
    LoginPage loginPage;

    @Given("^I navigate to SauceLab demo page (.*)$")
    public void navigate(String browser) {
        loginPage = new LoginPage(browser);
        loginPage.navigateToSauceLab();
    }

    @After
    public void closeDriver() {
        SetUp.quitDriver();
    }

    @AfterStep
    public void screenShot(Scenario scenario) {
        takeScreenShot(scenario);
    }
}
