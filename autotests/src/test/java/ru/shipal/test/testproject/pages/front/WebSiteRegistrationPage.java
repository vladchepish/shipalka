package ru.shipal.test.testproject.pages.front;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.shipal.test.testproject.objects.User;
import ru.shipal.test.testproject.pages.BasePage;

public class WebSiteRegistrationPage extends BasePage {

    public static final By CREATE_ACCOUNT_BTN = By.cssSelector("button[name=create_account]");
    public static final By TAX_ID_FIELD = By.cssSelector("form[name='customer_form'] input[name='tax_id']");
    public static final By COMPANY_FIELD = By.cssSelector("form[name='customer_form'] input[name='company']");
    public static final By FRST_NAME_FIELD = By.cssSelector("form[name='customer_form'] input[name='firstname']");
    public static final By LAST_NAME_FIELD = By.cssSelector("form[name='customer_form'] input[name='lastname']");
    public static final By FRST_ADDRESS_FIELD = By.cssSelector("form[name='customer_form'] input[name='address1']");
    public static final By SCND_ADDRESS_FIELD = By.cssSelector("form[name='customer_form'] input[name='address2']");
    public static final By POSTOCODE_FIELD = By.cssSelector("form[name='customer_form'] input[name='postcode']");
    public static final By CITY_FIELD = By.cssSelector("form[name='customer_form'] input[name='city']");
    public static final By COUNTRY_SELECT = By.cssSelector("span.select2-selection__arrow");
    public static final By EMAIL_FIELD = By.cssSelector("form[name='customer_form'] input[name='email']");
    public static final By PHONE_FIELD = By.cssSelector("form[name='customer_form'] input[name='phone']");
    public static final By PASSWORD_FIELD = By.cssSelector("form[name='customer_form'] input[name='password']");
    public static final By CONFIRM_PASSWORD_FIELD = By.cssSelector("form[name='customer_form'] input[name='confirmed_password']");

    public WebSiteRegistrationPage(WebDriver driver) {
        super(driver);
        shortWait.until(ExpectedConditions.visibilityOfElementLocated(CREATE_ACCOUNT_BTN));
    }

    public WebSiteRegistrationPage fillAllRequiredFields(User user) {
        findAndFeelField(TAX_ID_FIELD, user.getTaxID());
        findAndFeelField(COMPANY_FIELD, user.getCompany());
        findAndFeelField(FRST_NAME_FIELD, user.getFirstName());
        findAndFeelField(LAST_NAME_FIELD, user.getLastName());
        findAndFeelField(FRST_ADDRESS_FIELD, user.getFrstAddress());
        findAndFeelField(SCND_ADDRESS_FIELD, user.getScndAddress());
        findAndFeelField(POSTOCODE_FIELD, user.getPostocode());
        findAndFeelField(CITY_FIELD, user.getCity());
        findAndChoseOptionFromSelect(COUNTRY_SELECT, user.getCountry());
        findAndFeelField(EMAIL_FIELD, user.getEmail());
        findAndFeelField(PHONE_FIELD, user.getPhone());
        findAndFeelField(PASSWORD_FIELD, user.getPassword());
        findAndFeelField(CONFIRM_PASSWORD_FIELD, user.getPassword());
        return this;
    }

    public WebSiteHomePage registrateNewUser(User user){
        fillAllRequiredFields(user);
        getElement(CREATE_ACCOUNT_BTN).click();
        return new WebSiteHomePage(driver);
    }

}
