package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import utils.BasePage;
import java.util.HashMap;

import static locators.CheckoutLocators.*;
import static locators.DashboardLocators.cartIcon;
import static pages.DashboardPage.getSelectedItemAccess;

public class CheckoutPage extends BasePage
{
    public CheckoutPage(String browser) {
        super(browser);
    }

    public void proceedWithCheckout()
    {
        clickElement(By.xpath(cartIcon));
        clickElement(By.xpath(checkoutButton));
        writeText(By.xpath(txtFirstName), "Orlando");
        writeText(By.xpath(txtLastName), "Avila");
        writeText(By.xpath(txtZipCode), "40880");
        clickElement(By.xpath(continueButton));
    }

    public String getSubtotal()
    {
        return getElementText(By.xpath(subtotal));
    }

    public HashMap<String, Boolean> getCheckoutElements()
    {
        HashMap<String, Boolean> presentElements = new HashMap<>();

        try {
            presentElements.put("order_title", elementIsDisplayed(By.xpath(orderTitle)));
            presentElements.put("order_message", elementIsDisplayed(By.xpath(orderMessage)));
            presentElements.put("home_button", elementIsDisplayed(By.xpath(backToHomeButton)));
        }
        catch (TimeoutException e) {
            presentElements.put("order_title", false);
            presentElements.put("order_message", false);
            presentElements.put("home_button", false);
            e.printStackTrace();
        }

        return presentElements;
    }

    public String getItemsSum()
    {
        float subTotal = 0;

        for (Float selectedItemPrice : getSelectedItemAccess()) subTotal += selectedItemPrice;

        return "Item total: $" + subTotal;
    }

    public void clickFinishButton() {
        getSelectedItemAccess().clear();
        clickElement(By.xpath(finishButton));
    }
}