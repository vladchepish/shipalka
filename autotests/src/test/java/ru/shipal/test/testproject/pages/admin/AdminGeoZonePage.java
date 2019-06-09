package ru.shipal.test.testproject.pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.shipal.test.testproject.pages.BasePage;

import java.util.LinkedList;
import java.util.List;

public class AdminGeoZonePage extends BasePage {

    private static final By TABLE_ZONES = By.cssSelector("table#table-zones");
    private static final By ZONE_SELECTED_OPTION = By.xpath("//select[contains(@name, 'zone_code')]//option[@selected='selected']");

    public AdminGeoZonePage(WebDriver driver) {
        super(driver);
        shortWait.until(ExpectedConditions.visibilityOfElementLocated(TABLE_ZONES));
    }

    public LinkedList<String> getZonesList() {
        List<WebElement> elementsList = getElements(ZONE_SELECTED_OPTION);
        LinkedList<String> zonesList = new LinkedList<>();
        for (WebElement element : elementsList){
            zonesList.add(element.getText());
        }
        return zonesList;
    }

}
