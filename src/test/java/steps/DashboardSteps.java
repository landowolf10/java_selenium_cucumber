package steps;

import io.cucumber.java.en.And;
import pages.DashboardPage;
import utils.SetUp;

public class DashboardSteps {
    DashboardPage dashboardPage = new DashboardPage(SetUp.browser);

    @And("sort products from most expensive to cheapest")
    public void sortProduct()
    {
        dashboardPage.sortDropdown();
    }

    @And("add product to the cart")
    public void addProductToCart()
    {
        dashboardPage.addProduct();
    }
}
