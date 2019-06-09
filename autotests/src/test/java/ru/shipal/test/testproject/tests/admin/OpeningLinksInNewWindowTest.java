package ru.shipal.test.testproject.tests.admin;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.shipal.test.testproject.pages.Navigation;
import ru.shipal.test.testproject.pages.admin.AdminCountriesPage;
import ru.shipal.test.testproject.pages.admin.AdminEditCountryPage;
import ru.shipal.test.testproject.pages.admin.AdminLoginPage;
import ru.shipal.test.testproject.pages.admin.AdminMainPage;
import ru.shipal.test.testproject.tests.TestBase;

public class OpeningLinksInNewWindowTest extends TestBase {

    private static AdminLoginPage loginPage;
    private static Navigation navigation;
    private static AdminMainPage mainPage;
    private static AdminCountriesPage countriesPage;
    private static AdminEditCountryPage editCountryPage;

    @BeforeMethod
    public void beforeMEthod() {
        loginPage = new AdminLoginPage(driver);
        navigation = new Navigation(driver);
        navigation.goToAdminAuatorizationPage();
        mainPage = loginPage.login("admin", "admin");
    }

    @Test
    public void openingLinksInNewWindowTest(){
        countriesPage = mainPage.goToCountriesPage();
        editCountryPage = countriesPage.openFirstCountry();
        editCountryPage.openAllLinksInNewTab();
    }
}
