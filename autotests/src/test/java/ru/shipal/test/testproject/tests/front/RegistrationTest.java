package ru.shipal.test.testproject.tests.front;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.shipal.test.testproject.objects.User;
import ru.shipal.test.testproject.pages.Navigation;
import ru.shipal.test.testproject.pages.front.WebSiteHomePage;
import ru.shipal.test.testproject.pages.front.WebSiteMainPage;
import ru.shipal.test.testproject.pages.front.WebSiteRegistrationPage;
import ru.shipal.test.testproject.tests.TestBase;

import static ru.shipal.test.testproject.utils.DataGenerator.*;

public class RegistrationTest extends TestBase {

    private static Navigation navigation;
    private static WebSiteMainPage mainWebSitePage;
    private static WebSiteRegistrationPage registrationPage;
    private static WebSiteHomePage homePage;

    @BeforeMethod
    public void openPageMethod(){
        navigation = new Navigation(driver);
        mainWebSitePage = navigation.goToWebSiteMainPage();
    }

    @Test
    public void registrationTest(){
        User newUser = new User()
                .setTaxID(geniratorNumeric(5))
                .setCompany(generateString(5))
                .setFirstName(generateString(6))
                .setLastName(generateString(6))
                .setFrstAddress(generateString(5))
                .setScndAddress(generateString(5))
                .setPostocode(geniratorNumeric(5))
                .setCity(generateString(5))
                .setCountry("United States")
                .setEmail(generateEmail())
                .setPhone("+1" + geniratorNumeric(9))
                .setPassword(generatePassword(8));
        registrationPage = mainWebSitePage.openRegistrationPage();
        homePage = registrationPage.registrateNewUser(newUser);
        homePage.logout();
        mainWebSitePage.login(newUser);
    }
}
