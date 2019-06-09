package ru.shipal.test.testproject.tests.admin;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.shipal.test.testproject.pages.Navigation;
import ru.shipal.test.testproject.pages.admin.AdminCatalogPage;
import ru.shipal.test.testproject.pages.admin.AdminLoginPage;
import ru.shipal.test.testproject.pages.admin.AdminMainPage;
import ru.shipal.test.testproject.tests.TestBase;

public class AdminCheckLogOnProductPageTest extends TestBase {

    private static AdminLoginPage loginPage;
    private static Navigation navigation;
    private static AdminMainPage mainPage;
    private static AdminCatalogPage catalogPage;

    @BeforeMethod
    public void beforeMEthod() {
        loginPage = new AdminLoginPage(driver);
        navigation = new Navigation(driver);
        navigation.goToAdminAuatorizationPage();
        mainPage = loginPage.login("admin", "admin");
        catalogPage = mainPage.goToCatalogPage();
    }

    @Test
    public void checkLogsOnProductPageTest(){
        String categoryId = "1";
        catalogPage.openCategory(categoryId);
        catalogPage.openAllProductInCategoryAbdCheckLog(categoryId);
    }
}
