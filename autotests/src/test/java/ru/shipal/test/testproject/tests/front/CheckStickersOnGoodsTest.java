package ru.shipal.test.testproject.tests.front;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.shipal.test.testproject.pages.Navigation;
import ru.shipal.test.testproject.pages.front.WebSiteMainPage;
import ru.shipal.test.testproject.tests.TestBase;

public class CheckStickersOnGoodsTest extends TestBase {

    private static Navigation navigation;
    private static WebSiteMainPage mainWebSitePage;

    @BeforeMethod
    public void openPageMethod(){
        navigation = new Navigation(driver);
        mainWebSitePage = navigation.goToWebSiteMainPage();
    }

    @Test
    public void checkStickersOnGoodsTest(){
        mainWebSitePage.checkStickersOnGoods();
    }

}
