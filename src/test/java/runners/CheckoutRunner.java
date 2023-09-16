package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import utils.BasePage;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/checkout/checkout.feature",
        glue = "steps",
        plugin = {
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "json:target/cucumber-report.json"
        },
        monochrome = true
)

public class CheckoutRunner
{
}
