package ru.shipal.test.testproject.pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.shipal.test.testproject.pages.BasePage;

import java.util.LinkedList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class AdminGeoZonesPage extends BasePage {

    private static AdminGeoZonePage adminGeoZonePage;

    private static final By ADD_NEX_GEO_ZONE_BTN = By.xpath("//a[text()=' Add New Geo Zone']");
    private static final By LINE_IN_THE_TABLE = By.cssSelector("table.dataTable tr.row");


    public AdminGeoZonesPage(WebDriver driver) {
        super(driver);
        shortWait.until(ExpectedConditions.visibilityOfElementLocated(ADD_NEX_GEO_ZONE_BTN));
    }

    public int countCountries() {
        List<WebElement> countriesList = getElements(LINE_IN_THE_TABLE);
        return countriesList.size();
    }

    public void checkInfoOrderInside(int countCountries) {
        for (int i = 1; i <= countCountries; i++) {
            adminGeoZonePage = openGeoZonePage(i);
            LinkedList<String> zonesList = adminGeoZonePage.getZonesList();
            LinkedList<String> sortedZoneList = sortListByAlphabet(zonesList);
            assertEquals(zonesList.size(), sortedZoneList.size(),
                    "Количество элементов в списках должно совпадать");
            assertEquals(zonesList, sortedZoneList,
                    "Элементы списков должны совпадать");
            driver.navigate().back();
        }
    }

    public AdminGeoZonePage openGeoZonePage(int i){
        getElement(By.xpath("//table[@class='dataTable']//tr[@class='row'][" + i + "]/td[3]//a")).click();
        return new AdminGeoZonePage(driver);
    }

}
