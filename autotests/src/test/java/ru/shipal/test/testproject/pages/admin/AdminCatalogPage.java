package ru.shipal.test.testproject.pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.shipal.test.testproject.pages.BasePage;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class AdminCatalogPage extends BasePage {

    private static final By ADD_NEW_PRODUCT_BTN = By.cssSelector("a.button[href*='product']");
    private static final By ROW_IN_CATALOG_TABLE = By.cssSelector("tr.row");
    private static final By PRODUCT_LINKS = By.cssSelector("table.dataTable td:nth-child(3) a[href*='category_id=1&product_id']");

    public AdminCatalogPage(WebDriver driver) {
        super(driver);
        shortWait.until(ExpectedConditions.visibilityOfElementLocated(ADD_NEW_PRODUCT_BTN));
    }

    public int getProductsNumber() {
        int numder = getElements(ROW_IN_CATALOG_TABLE).size();
        return numder;
    }

    public AdminAddNewProductPage clickAddNewProductBtn() {
        clickByElement(ADD_NEW_PRODUCT_BTN);
        return new AdminAddNewProductPage(driver);
    }

    public AdminCatalogPage openCategory(String categoryId) {
        clickByElement(By.xpath("//td[3]/a[contains(@href, 'category_id=" + categoryId + "')]"));
        return this;
    }

    public AdminCatalogPage openAllProductInCategoryAbdCheckLog(String categoryID) {
        List<WebElement> productsList = getElements(
                By.cssSelector("table.dataTable td:nth-child(3) a[href*='category_id=" + categoryID + "&product_id']"));
        assertTrue(productsList.size() > 0,
                "Количество товаров в категории должно быть больше нуля");
        List<String> productsIdList = new ArrayList<>();
        for (WebElement product : productsList){
            String id = product.getAttribute("href");
            productsIdList.add(id);
        }
        for (int i = 0; i <productsList.size(); i++){
            clickByElement(By.cssSelector("a[href*='" + productsIdList.get(i) + "']"));
            List<LogEntry> logList = driver.manage().logs().get("browser").getAll();
            assertTrue(logList.size() == 0,
                    "Количество сообщений в консоле браузера должно быть равно нулю");
            returnOnPreviesPage();
        }
        return this;
    }

}
