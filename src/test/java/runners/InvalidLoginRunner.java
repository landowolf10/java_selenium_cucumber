package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import utils.BasePage;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/login/invalid_login.feature",
        glue = "steps"
)

public class InvalidLoginRunner
{
}
