package steps;

import io.cucumber.java.en.And;
import pages.DashboardPage;
import utils.Clients;

public class DashboardSteps {
    DashboardPage dashboardPage = new DashboardPage(Clients.browser);

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
