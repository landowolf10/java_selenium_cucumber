package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import utils.BasePage;
import utils.ConstantData;

import java.util.HashMap;

import static locators.DashboardLocators.cartIcon;
import static locators.DashboardLocators.sortDropDown;
import static locators.LoginLocators.*;

public class LoginPage extends BasePage {
    public LoginPage(String browser) {
        super(browser);
    }

    public void navigateToSauceLab() {
        navigateTo(ConstantData.URL);
    }

    public void clickLoginButton() {
        clickElement(By.xpath(loginButton));
    }

    public void writeCredentials(String email, String password) {
        writeText(By.xpath(userTextbox), email);
        writeText(By.xpath(passwordTextbox), password);
    }

    public HashMap<String, Boolean> getValidLoginElements()
    {
        HashMap<String, Boolean> presentElements = new HashMap<>();

        try {
            presentElements.put("cart_icon", elementIsDisplayed(By.xpath(cartIcon)));
            presentElements.put("drop_down", elementIsDisplayed(By.xpath(sortDropDown)));
        }
        catch (TimeoutException e) {
            presentElements.put("cart_icon", false);
            presentElements.put("drop_down", false);
            e.printStackTrace();
        }

        return presentElements;
    }

    public HashMap<String, Boolean> getInvalidLoginElements()
    {
        HashMap<String, Boolean> presentElements = new HashMap<>();

        presentElements.put("login_button", elementIsDisplayed(By.xpath(loginButton)));
        presentElements.put("error_message", elementIsDisplayed(By.xpath(errorMessage)));

        return presentElements;
    }


    public String getErrorMessageText() {
        return getElementText(By.xpath(errorMessage));
    }
}
