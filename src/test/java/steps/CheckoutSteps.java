package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.CheckoutPage;
import utils.Clients;

public class CheckoutSteps
{
    CheckoutPage checkout = new CheckoutPage(Clients.browser);

    @And("checkout information")
    public void checkoutInfo()
    {
        checkout.proceedWithCheckout();
        Assert.assertEquals(checkout.getItemsSum(), checkout.getSubtotal());
        checkout.clickFinishButton();
    }

    @Then("finish checkout")
    public void finishCheckout()
    {
        Assert.assertTrue(checkout.getCheckoutElements().get("order_title"));
        Assert.assertTrue(checkout.getCheckoutElements().get("order_message"));
        Assert.assertTrue(checkout.getCheckoutElements().get("home_button"));
    }
}