package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected static WebDriver chromeDriver;
    private static Actions action;
    Clients clients = new Clients();

    public BasePage(String browser) {
        chromeDriver = clients.getDriver(browser);
    }

    private WebElement getElementBy(By elementLocator)
    {
        return Clients.wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
    }

    public List<WebElement> getAllElementsBy(By elementLocator)
    {
        return chromeDriver.findElements(elementLocator);
    }

    public void navigateTo(String url) {
        chromeDriver.get(url);
    }

    public void goToLinkText(String linkText) {
        chromeDriver.findElement(By.linkText(linkText));
    }

    public void clickElement(By elementLocator) {
        getElementBy(elementLocator).click();
    }

    public void clickElementFromList(WebElement element)
    {
        element.click();
    }

    public void writeText(By elementLocator, String text) {
        getElementBy(elementLocator).clear();
        getElementBy(elementLocator).sendKeys(text);
    }

    public String getElementText(By elementLocator) {
        return getElementBy(elementLocator).getText();
    }

    public void selectFromDropDownByValue(By elementLocator, String valueToSelect) {
        Select dropdown = new Select(getElementBy(elementLocator));
        dropdown.selectByValue(valueToSelect);
    }

    public void selectFromDropDownByIndex(By elementLocator, int valueToSelect) {
        Select dropdown = new Select(getElementBy(elementLocator));
        dropdown.selectByIndex(valueToSelect);
    }

    public void selectFromDropDownByText(By elementLocator, String valueToSelect) {
        Select dropdown = new Select(getElementBy(elementLocator));
        dropdown.selectByVisibleText(valueToSelect);
    }

    public void hoverOverElement(By elementLocator) {
        action.moveToElement(getElementBy(elementLocator));
    }

    public void doubleClick(By elementLocator) {
        action.doubleClick(getElementBy(elementLocator));
    }

    public void rightClick(By elementLocator) {
        action.contextClick(getElementBy(elementLocator));
    }

    public String getValueFromTable(By elementLocator, int row, int column) {
        String cell = elementLocator + "/table/tbody/tr[" + row + "]/td[" + column + "]";

        System.out.print("Cell locator: " + cell);

        return getElementBy(By.xpath(cell)).getText();
    }

    public void setValueOnTable(By elementLocator, int row, int column, String value) {
        String cell = elementLocator + "/table/tbody/tr[" + row + "]/td[" + column + "]";

        getElementBy(By.xpath(cell)).sendKeys(value);
    }

    public void switchToiFrame(int iFrameIndex) {
        chromeDriver.switchTo().frame(iFrameIndex);
    }

    public void switchToParentFrame() {
        chromeDriver.switchTo().parentFrame();
    }

    public void dismissAlert() {
        chromeDriver.switchTo().alert().dismiss();
    }

    public void waitUntilElementLocated(By locatorType, int maxWaitSec) {
        WebDriverWait wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(maxWaitSec));

        wait.until(ExpectedConditions.visibilityOfElementLocated(locatorType));
    }

    public boolean elementIsDisplayed(By locatorType) {
        return getElementBy(locatorType).isDisplayed();
    }

    public boolean elementIsSelected(By locatorType) {
        return getElementBy(locatorType).isSelected();
    }

    public boolean elementIsEnabled(By locatorType) {
        return getElementBy(locatorType).isEnabled();
    }
}
