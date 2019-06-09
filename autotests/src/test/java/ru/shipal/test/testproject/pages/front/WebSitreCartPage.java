package ru.shipal.test.testproject.pages.front;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.shipal.test.testproject.pages.BasePage;

import java.util.List;

public class WebSitreCartPage extends BasePage {

    private static final By CUSTOMER_SERVICE_WRAPER = By.cssSelector("div#customer-service-wrapper");
    private static final By LINE_IN_BOTTOM_TABLE = By.xpath("//tr[td[@class='item']][1]");
    private static final By DELETE_PRODUCT_BTN = By.cssSelector("button[name='remove_cart_item']");
    private static final By TABLE_FOOTER = By.cssSelector("tr.footer");
    private static final By SHORTCUT = By.cssSelector("li.shortcut a");

    public WebSitreCartPage(WebDriver driver) {
        super(driver);
        shortWait.until(ExpectedConditions.visibilityOfElementLocated(CUSTOMER_SERVICE_WRAPER));
    }

    public void deleteAllProductsFromCart() {
        shortWait.until(ExpectedConditions.visibilityOfElementLocated(TABLE_FOOTER));
        int productsNumber = getElements(SHORTCUT).size();
        for (int i = productsNumber; i > 0; i--){
            List<WebElement> rows = getElements(LINE_IN_BOTTOM_TABLE);
            getElement(DELETE_PRODUCT_BTN).click();
            shortWait.until(ExpectedConditions.stalenessOf(rows.get(rows.size()-1)));
        }
    }

}
