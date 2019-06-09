package ru.shipal.test.testproject.pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.shipal.test.testproject.pages.BasePage;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class AdminEditCountryPage extends BasePage {

    public static final By MAIN_FORM = By.xpath("//form[contains(@enctype, 'multipart')]");
    public static final By ZONES_TABLE = By.cssSelector("table#table-zones");
    public static final By GEO_ZONE_NAME_IN_TABLE = By.cssSelector("tr:not(.header) td:nth-child(3)");
    public static final By LINKS_OPENING_IN_NEW_TAB = By.cssSelector("form[enctype='multipart/form-data'] a[target='_blank']");

    public AdminEditCountryPage(WebDriver driver) {
        super(driver);
        shortWait.until(ExpectedConditions.visibilityOfElementLocated(MAIN_FORM));
    }

    public LinkedList<String> getGeoZonesNames(){
        List<WebElement> linesList = getElement(ZONES_TABLE).findElements(GEO_ZONE_NAME_IN_TABLE);
        LinkedList<String> geoZones = new LinkedList<>();
        for (WebElement line : linesList){
            String value = line.findElement(By.cssSelector("input")).getAttribute("value");
            if (!value.equals("")){
                geoZones.add(value);
            }
        }
        return geoZones;
    }

    public AdminEditCountryPage openAllLinksInNewTab() {
        List<WebElement> linksList = getElements(LINKS_OPENING_IN_NEW_TAB);
        String mainWindow = driver.getWindowHandle();
        Set<String> oldWindows = driver.getWindowHandles();
        for (WebElement item : linksList){
            item.click();
            String newWindow = shortWait.until(thereIsWindowOtherThan(oldWindows));
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(mainWindow);
        }
        return this;
    }

    public ExpectedCondition<String> thereIsWindowOtherThan(Set<String> oldWindows){
        return new ExpectedCondition<String>() {
            public String apply(WebDriver driver){
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }
}
