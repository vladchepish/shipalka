package ru.shipal.test.testproject.tests.front;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.shipal.test.testproject.pages.Navigation;
import ru.shipal.test.testproject.pages.front.WebSiteMainPage;
import ru.shipal.test.testproject.pages.front.WebSitreCartPage;
import ru.shipal.test.testproject.tests.TestBase;

public class GoodsInCartTest extends TestBase {

    private static Navigation navigation;
    private static WebSiteMainPage mainPage;
    private static WebSitreCartPage cartPage;

    @BeforeMethod
    public void beforeMethod(){
        navigation = new Navigation(driver);
        mainPage = navigation.goToWebSiteMainPage();
    }

    @Test
    public void goodsInCartTest(){
        mainPage.addGoodsToCart(3);
        cartPage = mainPage.openCart();
        cartPage.deleteAllProductsFromCart();
    }

}
