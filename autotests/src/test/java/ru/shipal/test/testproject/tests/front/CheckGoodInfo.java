package ru.shipal.test.testproject.tests.front;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.shipal.test.testproject.objects.Good;
import ru.shipal.test.testproject.pages.Navigation;
import ru.shipal.test.testproject.pages.front.WebSiteMainPage;
import ru.shipal.test.testproject.pages.front.WebSiteProductPage;
import ru.shipal.test.testproject.tests.TestBase;

import static org.testng.Assert.assertEquals;

public class CheckGoodInfo extends TestBase {

    private static Navigation navigation;
    private static WebSiteMainPage mainWebSitePage;
    private static WebSiteProductPage productPage;

    @BeforeMethod
    public void openPageMethod(){
        navigation = new Navigation(driver);
        mainWebSitePage = navigation.goToWebSiteMainPage();
    }

    @Test
    public void checkGoodInfo(){
        Good goodOnMainPage = mainWebSitePage.getGoodInfoFromComparingBox().get(0);
        productPage = mainWebSitePage.openFirstGoodFullPage();
        Good goodFromFullPage = productPage.getGood();
        productPage.checkAttribytes();
        assertEquals(goodOnMainPage,
                goodFromFullPage,
                "Информация о товарах должна совпадать");
    }
}
