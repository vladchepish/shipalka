package ru.shipal.test.testproject.tests.admin;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.shipal.test.testproject.pages.Navigation;
import ru.shipal.test.testproject.pages.admin.AdminCountriesPage;
import ru.shipal.test.testproject.pages.admin.AdminGeoZonesPage;
import ru.shipal.test.testproject.pages.admin.AdminLoginPage;
import ru.shipal.test.testproject.pages.admin.AdminMainPage;
import ru.shipal.test.testproject.tests.TestBase;

import java.util.LinkedList;

public class AdminSortingCountriesTest extends TestBase {

    private static AdminLoginPage loginPage;
    private static Navigation navigation;
    private static AdminMainPage mainPage;
    private static AdminCountriesPage adminCountriesPage;
    private static AdminGeoZonesPage adminGeoZonesPage;

    @BeforeMethod
    public void beforeMEthod() {
        loginPage = new AdminLoginPage(driver);
        navigation = new Navigation(driver);
        navigation.goToAdminAuatorizationPage();
        mainPage = loginPage.login("admin", "admin");
    }

    @Test
    public void sortingCountriesTest() {
        adminCountriesPage = mainPage.goToCountriesPage();
        LinkedList<String> countrysNameList = adminCountriesPage.getCountriesList();
        LinkedList<String> sortedCountysNameList = adminCountriesPage.sortListByAlphabet(countrysNameList);
        adminCountriesPage.compareCountryLists(countrysNameList, sortedCountysNameList);
    }

    @Test
    public void sortingLocationInGeoZone() {
        adminGeoZonesPage = mainPage.goToGeoZonePage();
        int countCountries = adminGeoZonesPage.countCountries();
        adminGeoZonesPage.checkInfoOrderInside(countCountries);
    }

    @Test
    public void sortingIntoCountriesTest(){
        adminCountriesPage = mainPage.goToCountriesPage();
        LinkedList<String> notEmptyCountries = adminCountriesPage.getNotEmptyCountries();
        System.out.println(notEmptyCountries.size());
        adminCountriesPage.goInsideCountryAndCheckGeoZOneOrder(notEmptyCountries);
    }
}
