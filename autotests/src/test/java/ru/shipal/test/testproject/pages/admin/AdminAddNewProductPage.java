package ru.shipal.test.testproject.pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.shipal.test.testproject.objects.Good;
import ru.shipal.test.testproject.pages.BasePage;

public class AdminAddNewProductPage extends BasePage {

    /** Tabs */
    private static final By GENERAL_TAB = By.cssSelector("a[href='#tab-general']");
    private static final By INFORMATION_TAB = By.cssSelector("a[href='#tab-information']");
    private static final By PRICE_TAB = By.cssSelector("a[href='#tab-prices']");
    /** Tab General */
    private static final By ENABLED_RADIO_BTN = By.xpath("//label[text()=' Enabled']//input");
    private static final By NAME_INPUT = By.cssSelector("input[name='name[en]']");
    private static final By CODE_INPUT = By.cssSelector("input[name='code']");
    private static final By PHOTO_INPUT = By.cssSelector("input[name='new_images[]']");
    /** Tab Information */
    private static final By MANUFACTURER_SELECT = By.cssSelector("select[name='manufacturer_id']");
    private static final By DESCRIPTION_TEXTAREA = By.cssSelector("textarea[name*='description']");
    /** Tab Price */
    private static final By PURCHASE_PRICE_INPUT = By.cssSelector("input[name='purchase_price']");
    private static final By REGULAR_PRICE_USD =By.cssSelector("input[name='prices[USD]']");
    /** Button Bar At The Bottom */
    private static final By SAVE_BTN = By.cssSelector("button[name='save']");


    public AdminAddNewProductPage(WebDriver driver) {
        super(driver);
        shortWait.until(ExpectedConditions.visibilityOfElementLocated(GENERAL_TAB));
    }

    public void createNewProduct(Good product) {
        clickByElement(GENERAL_TAB);
        setProductEnable();
        findAndFeelField(NAME_INPUT, product.getName());
        findAndFeelField(CODE_INPUT, product.getCode());
        attach(PHOTO_INPUT, product.getPhoto());
        clickByElement(INFORMATION_TAB);
        shortWait.until(ExpectedConditions.visibilityOfElementLocated(MANUFACTURER_SELECT));
        findAndFeelField(DESCRIPTION_TEXTAREA, product.getDescription());
        clickByElement(PRICE_TAB);
        shortWait.until(ExpectedConditions.visibilityOfElementLocated(PURCHASE_PRICE_INPUT));
        findAndFeelField(REGULAR_PRICE_USD, product.getRegularPrice());
        clickByElement(SAVE_BTN);
    }

    private void setProductEnable() {
        if (!getElement(ENABLED_RADIO_BTN).isSelected()){
            clickByElement(ENABLED_RADIO_BTN);
        }
    }

}
