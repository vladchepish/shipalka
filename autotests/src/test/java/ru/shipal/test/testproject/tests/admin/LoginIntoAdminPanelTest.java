package ru.shipal.test.testproject.tests.admin;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.shipal.test.testproject.pages.Navigation;
import ru.shipal.test.testproject.pages.admin.AdminLoginPage;
import ru.shipal.test.testproject.pages.admin.AdminMainPage;
import ru.shipal.test.testproject.tests.TestBase;

public class LoginIntoAdminPanelTest extends TestBase {

    private static AdminLoginPage loginPage;
    private static Navigation navigation;
    private static AdminMainPage mainPage;

    @BeforeMethod
    public void openPageMethod(){
        loginPage = new AdminLoginPage(driver);
        navigation = new Navigation(driver);
        navigation.goToAdminAuatorizationPage();
    }

    @Test
    public void loginIntoAdminPanelTest(){
        mainPage = loginPage.login("admin", "admin");
    }
}
