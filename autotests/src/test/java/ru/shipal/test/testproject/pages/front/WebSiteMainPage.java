package ru.shipal.test.testproject.pages.front;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import ru.shipal.test.testproject.objects.Good;
import ru.shipal.test.testproject.objects.User;
import ru.shipal.test.testproject.pages.BasePage;

import java.util.LinkedList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class WebSiteMainPage extends BasePage {

    private static final By GOODS_ITEM = By.cssSelector("ul.products li");
    private static final By STICKER = By.cssSelector("div.sticker");
    private static final By CART = By.cssSelector("div#cart");
    private static final By CART_LINK = By.cssSelector("a[href*='checkout']");

    private static final By COMPARING_SECTION = By.cssSelector("div#box-campaigns");
    private static final By MOST_POPULAR_SECTION = By.cssSelector("#box-most-popular");

    private static final By GOOD_ITEM = By.cssSelector("li.product");
    private static final By GOOD_NAMNE = By.cssSelector("div.name");
    private static final By REGULAR_PRICE = By.cssSelector("s.regular-price");
    private static final By COMPARING_PRICE = By.cssSelector("strong.campaign-price");
    //Login and Registration form in left sidebar
    private static final By EMAIL_FIELD_IN_LOGIN_FORM = By.cssSelector("form[name='login_form'] input[name='email']");
    private static final By PASSWEORD_FIELD_IN_LOGIN_FORM = By.cssSelector("form[name='login_form'] input[name='password']");
    private static final By LOGIN_BTN_IN_LOGIN_FORM = By.cssSelector("form[name='login_form'] button[name='login']");
    private static final By GO_TO_REGISTRATION_LINK = By.cssSelector("form[name='login_form'] a");


    public WebSiteMainPage(WebDriver driver) {
        super(driver);
        shortWait.until(ExpectedConditions.visibilityOfElementLocated(CART));
    }

    public void checkStickersOnGoods() {
        List<WebElement> goodsList = getElements(GOODS_ITEM);
        assertTrue(goodsList.size() > 0,
                "Количество товаров на главной странице сайта должно быть больше нуля");
        for(WebElement goodItem : goodsList){
            List<WebElement> stickersList = goodItem.findElements(STICKER);
            assertTrue(stickersList.size() == 1,
                    "Каждый товар должен иметь по одному стикеру");
        }
    }

    public LinkedList<Good> getGoodInfoFromComparingBox() {
        LinkedList<Good> goodsList = new LinkedList<>();
        WebElement comparingBox = getElement(COMPARING_SECTION);
        List<WebElement> goodsElementLIst = comparingBox.findElements(GOOD_ITEM);
        assertTrue(goodsElementLIst.size() > 0,
                "Количество товаров в блоке должно быть больше нуля");
        for (WebElement goodInComparingBox : goodsElementLIst){
            WebElement goodName = goodInComparingBox.findElement(GOOD_NAMNE);
            WebElement goodRegularPrice = goodInComparingBox.findElement(REGULAR_PRICE);
            WebElement goodComparingPrice = goodInComparingBox.findElement(COMPARING_PRICE);
            assertTrue(goodName.isDisplayed(),
                    "Название товара должно отображаться");
            assertTrue(goodRegularPrice.isDisplayed(),
                    "Старая цена товара должно отображаеться");
            assertTrue(goodComparingPrice.isDisplayed(),
                    "Акционная цена товара должно отображаеться");
            goodsList.add(new Good()
                    .setName(goodName.getText())
                    .setRegularPrice(goodRegularPrice.getText())
                    .setComparingPrice(goodComparingPrice.getText()));
            checkAttribytes(goodRegularPrice, goodComparingPrice);
        }
        return goodsList;
    }

    private void checkAttribytes(WebElement regPrice, WebElement compPrice) {
        assertTrue(isRegularPriceGrey(regPrice.getCssValue("color")),
                "Цвет обычной цены должен быть серым");
        assertTrue(regPrice.getCssValue("text-decoration").contains("line-through"),
                "Значение обычной цены должно быть перечёркнутым");
        assertTrue(isComparingPriceColorRed(compPrice.getCssValue("color")),
                "Цвет акционной цены должен быть красным");
        assertTrue(compPrice.getCssValue("font-weight").contains("700") ||
                        compPrice.getCssValue("font-weight").contains("900"),
                "Значение акционной цены должно быть жирным. Actual: " + compPrice.getCssValue("font-weight"));
        assertTrue(Float.parseFloat(regPrice.getCssValue("font-size").replaceAll("px", ""))
                        < Float.parseFloat(compPrice.getCssValue("font-size").replaceAll("px", "")),
                "Акционная цена должна быть большего размера, нежели обычная");
    }

    public WebSiteProductPage openFirstGoodFullPage() {
        WebElement comparingBox = getElement(COMPARING_SECTION);
        WebElement goodsElementLIst = comparingBox.findElements(GOOD_ITEM).get(0);
        goodsElementLIst.findElement(By.cssSelector("a")).click();
        return new WebSiteProductPage(driver);
    }

    public WebSiteProductPage openFirstGoodFromPopular(){
        WebElement box = getElement(MOST_POPULAR_SECTION);
        WebElement goodsElementLIst = box.findElements(GOOD_ITEM).get(0);
        goodsElementLIst.findElement(By.cssSelector("a")).click();
        return new WebSiteProductPage(driver);
    }

    public WebSiteRegistrationPage openRegistrationPage() {
        getElement(GO_TO_REGISTRATION_LINK).click();
        return new WebSiteRegistrationPage(driver);
    }

    public WebSiteHomePage login(User user){
        findAndFeelField(EMAIL_FIELD_IN_LOGIN_FORM, user.getEmail());
        findAndFeelField(PASSWEORD_FIELD_IN_LOGIN_FORM, user.getPassword());
        clickByElement(LOGIN_BTN_IN_LOGIN_FORM);
        return new WebSiteHomePage(driver);
    }

    public void addGoodsToCart(int numberGoods) {
        for (int i = 0; i < numberGoods; i++){
            WebSiteProductPage webSite = openFirstGoodFromPopular();
            webSite.addGoodToCard();
        }
    }

    public WebSitreCartPage openCart() {
        clickByElement(CART_LINK);
        return new WebSitreCartPage(driver);
    }

}
