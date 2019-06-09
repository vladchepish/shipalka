package ru.shipal.test.testproject.pages.front;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.shipal.test.testproject.objects.Good;
import ru.shipal.test.testproject.pages.BasePage;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class WebSiteProductPage extends BasePage {

    private static final By BOX_PRODUCT = By.cssSelector("div#box-product");
    private static final By PRODUCT_NAME = By.cssSelector("h1.title");
    private static final By REGULAR_PRICE = By.cssSelector("s.regular-price");
    private static final By COMPARING_PRICE = By.cssSelector("strong.campaign-price");
    private static final By ADD_PRODUCT_TO_CART = By.cssSelector("button[name=add_cart_product]");
    private static final By NUMBER_ITEMS_IN_CART = By.cssSelector("span[class='quantity']");
    private static final By SELECT_SIZE = By.cssSelector("select[name='options[Size]']");
    private static final By SELECT_SIZE_OPTIONS = By.cssSelector("select[name='options[Size]'] option");

    public WebSiteProductPage(WebDriver driver) {
        super(driver);
        shortWait.until(ExpectedConditions.visibilityOfElementLocated(BOX_PRODUCT));
    }

    public Good getGood() {
        return new Good()
                .setName(getElement(PRODUCT_NAME).getText())
                .setRegularPrice(getElement(REGULAR_PRICE).getText())
                .setComparingPrice(getElement(COMPARING_PRICE).getText());
    }

    public void checkAttribytes() {
        assertTrue(getElement(REGULAR_PRICE).getCssValue("color").contains("102, 102, 102"),
                "Цвет обычной цены должен быть серым");
        assertTrue(getElement(REGULAR_PRICE).getCssValue("text-decoration").contains("line-through"),
                "Значение обычной цены должно быть перечёркнутым");
        assertTrue(getElement(COMPARING_PRICE).getCssValue("color").contains("204, 0, 0"),
                "Цвет акционной цены должен быть красным");
        assertTrue(getElement(COMPARING_PRICE).getCssValue("font-weight").contains("700"),
                "Значение акционной цены должно быть жирным");
        assertTrue(Float.parseFloat(getElement(REGULAR_PRICE).getCssValue("font-size").replaceAll("px", ""))
                        < Float.parseFloat(getElement(COMPARING_PRICE).getCssValue("font-size").replaceAll("px", "")),
                "Акционная цена должна быть большего размера, нежели обычная");
    }

    public WebSiteProductPage addGoodToCard(){
        String itemsInCart = getElement(NUMBER_ITEMS_IN_CART).getText();
        if (getElements(SELECT_SIZE).size() > 0){
            List<WebElement> options = getElements(SELECT_SIZE_OPTIONS);
            findAndSelectOptionFromSelect(SELECT_SIZE, options.get(1).getText());
        }
        getElement(ADD_PRODUCT_TO_CART).click();
        shortWait.until(ExpectedConditions.not(ExpectedConditions.textToBe(By.cssSelector("span[class='quantity']"), itemsInCart)));
        returnOnPreviesPage();
        return this;
    }

}
