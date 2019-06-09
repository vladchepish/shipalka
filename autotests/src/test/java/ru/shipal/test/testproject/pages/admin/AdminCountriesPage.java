package ru.shipal.test.testproject.pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.shipal.test.testproject.pages.BasePage;

import java.util.LinkedList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AdminCountriesPage extends BasePage {

    private static AdminEditCountryPage editCountryPage;

    private static final By COUNTRIES_FORM = By.cssSelector("form[name='countries_form']");
    private static final By LINE_IN_COUNTRY_TABLE = By.xpath("//table[@class='dataTable']//tr[@class='row']");

    public AdminCountriesPage(WebDriver driver) {
        super(driver);
        shortWait.until(ExpectedConditions.visibilityOfElementLocated(COUNTRIES_FORM));
    }

    public LinkedList<String> getCountriesList() {
        List<WebElement> countriesList = getElements(LINE_IN_COUNTRY_TABLE);
        LinkedList<String> countriesNameList = new LinkedList<>();
        for (WebElement country : countriesList){
            String countryName = country.findElement(By.xpath(".//td[5]")).getText();
            countriesNameList.add(countryName);
        }
        return countriesNameList;
    }

    public void compareCountryLists(List<String> countrysNameList, List<String> sortedCountysNameList) {
        assertTrue(countrysNameList.size() == sortedCountysNameList.size(),
                "Количество элементов списков должно совпадать");
        assertEquals(countrysNameList, sortedCountysNameList,
                "Элементы списков не соответствуют");
    }

    public LinkedList<String> getNotEmptyCountries() {
        List<WebElement> countriesList = getElements(LINE_IN_COUNTRY_TABLE);
        LinkedList<String> countriesNameList = new LinkedList<>();
        for (WebElement country : countriesList){
            //int numberGeoZones = country.findElement(By.xpath(".//td[6]")).getText();
            if (!country.findElement(By.xpath(".//td[6]")).getText().equals("0")){
                countriesNameList.add(country.findElement(By.xpath(".//td[5]")).getText());
            }
        }
        return countriesNameList;
    }

    public void goInsideCountryAndCheckGeoZOneOrder(LinkedList<String> list) {
        assertTrue(list.size() > 0,
                "Количество стран должно быть больше нуля");
        for (String country : list){
            editCountryPage = openEditCountryPageByName(country);
            LinkedList<String> geoZones = editCountryPage.getGeoZonesNames();
            LinkedList<String> sortedGeoZones = sortListByAlphabet(geoZones);
            assertEquals(geoZones,
                    sortedGeoZones,
                    "Списки должны совпадать");
            driver.navigate().back();
        }
    }

    public AdminEditCountryPage openEditCountryPageByName(String name){
        getElement(By.xpath("//td//a[text()='" + name + "']")).click();
        return new AdminEditCountryPage(driver);
    }

    public AdminEditCountryPage openFirstCountry() {
        getElement(LINE_IN_COUNTRY_TABLE).findElement(By.xpath("//td[5]//a")).click();
        return new AdminEditCountryPage(driver);
    }

}
