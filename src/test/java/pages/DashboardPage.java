package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.BasePage;

import java.util.ArrayList;
import java.util.List;

import static locators.DashboardLocators.*;

public class DashboardPage extends BasePage {
    static List<Float> selectedItemPrices = new ArrayList<>();

    public DashboardPage(String browser) {
        super(browser);
    }

    public void sortDropdown() {
        selectFromDropDownByText(By.xpath(sortDropDown), "Price (high to low)");
    }

    private List<Float> getPrices()
    {
        int elements = getAllElementsBy(By.xpath(productPrice)).size();
        List<Float> prices = new ArrayList<>();

        for (int i = 0; i < elements; i++)
            prices.add(Float.valueOf(getAllElementsBy(By.xpath(productPrice)).
                    get(i).getText().substring(1)));

        return prices;
    }

    public void addProduct()
    {
        List<WebElement> webElements = getAllElementsBy(By.xpath(addToCartButton));
        List<Float> prices = getPrices();

        for (int i = 0; i < prices.size(); i++) {
            if (prices.get(i) < 20)
            {
                clickElementFromList(webElements.get(i));
                prices.remove(i);
                webElements.remove(i);
                selectedItemPrices.add(prices.get(i));

                break;
            }
        }
    }

    public static List<Float> getSelectedItemAccess() {
        System.out.println("selectedItemPrices: " + selectedItemPrices);

        return selectedItemPrices;
    }
}
