package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.LoginPage;
import utils.Clients;

public class LoginSteps {
    LoginPage loginPage = new LoginPage(Clients.browser);

    @When("^type the username (.*) with password (.*)$")
    public void typeCredentials(String user, String password) {
        loginPage.writeCredentials(user, password);
    }

    @And("^press the login button$")
    public void pressLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("^verify that the user successfully logged in$")
    public void verifySuccessfulLogin() {
        Assert.assertTrue(loginPage.getValidLoginElements().get("cart_icon"));
        Assert.assertTrue(loginPage.getValidLoginElements().get("drop_down"));
    }

    @Then("^verify user login was not successful$")
    public void invalidLogin() {
        Assert.assertTrue(loginPage.getInvalidLoginElements().get("login_button"));
        Assert.assertTrue(loginPage.getInvalidLoginElements().get("error_message"));
        Assert.assertEquals(loginPage.getErrorMessageText(), "Epic sadface: Username and password do not match " +
                "any user in this service");
        //System.out.print("Element present: " + loginPage.getValidLoginElements().get("cart_icon"));
        //Assert.assertFalse("Cart icon not present", loginPage.getPresentElements().get("cart_icon"));
        //Assert.assertFalse("Dropdown not present", loginPage.getPresentElements().get("drop_down"));
    }
}
