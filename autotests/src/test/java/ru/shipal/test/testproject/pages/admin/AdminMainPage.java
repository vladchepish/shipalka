package ru.shipal.test.testproject.pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.shipal.test.testproject.pages.BasePage;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class AdminMainPage extends BasePage {

    private static final By LEFT_SIDEBAR = By.cssSelector("td[id='sidebar']");
    private static final By LEFT_MENU_ITEM = By.cssSelector("ul#box-apps-menu li#app-");
    private static final By LEFT_MENU_SUBITEM = By.cssSelector("ul.docs li");
    private static final By PAGE_HEADER = By.cssSelector("td#content h1");
    private static final By LEFT_MENU_COUNTRIES_LINK = By.xpath("//li//a[contains(@href, 'countries')]");
    private static final By LEGT_MENU_GEO_ZONE_LINK = By.xpath("//li//a[contains(@href, 'geo_zone')]");
    private static final By LEFT_MENU_LOGOUT_BTN = By.xpath("//a[contains(@href, 'logout')]");
    private static final By LEFT_MENU_CATALOG_LINK = By.cssSelector("li#app->a[href*='catalog']");

    public AdminMainPage(WebDriver driver) {
        super(driver);
        shortWait.until(ExpectedConditions.visibilityOfElementLocated(LEFT_SIDEBAR));
    }

    public AdminCountriesPage goToCountriesPage(){
        getElement(LEFT_MENU_COUNTRIES_LINK).click();
        return new AdminCountriesPage(driver);
    }

    public AdminGeoZonesPage goToGeoZonePage() {
        getElement(LEGT_MENU_GEO_ZONE_LINK).click();
        return new AdminGeoZonesPage(driver);
    }

    public AdminCatalogPage goToCatalogPage() {
        clickByElement(LEFT_MENU_CATALOG_LINK);
        return new AdminCatalogPage(driver);
    }

    public void clickEachItemAndSubItemLeftMenu(){
        List<WebElement> listItems = getElements(LEFT_MENU_ITEM);
        assertTrue(listItems.size() != 0, "Количество пунктов лефого меню должно быть больше нуля");
        for(int i = 1; i <= listItems.size(); i++){
            String locator = "//ul[@id='box-apps-menu']/li[@id='app-'][" + i + "]//a";
            getElement(By.xpath(locator)).click();
            assertTrue(getElement(PAGE_HEADER).isDisplayed(),
                    "Заголовок страницы не отображается");
            List<WebElement> listSubItems = getElements(LEFT_MENU_SUBITEM);
            if (listSubItems.size() > 0){
                for (int j = 1; j <= listSubItems.size(); j++){
                    String subListlocator = "//ul[@class='docs']/li[" + j + "]/a";
                    getElement(By.xpath(subListlocator)).click();
                    assertTrue(getElement(PAGE_HEADER).isDisplayed(),
                            "Заголовок страницы не отображается");
                }
            }
        }
    }

}
