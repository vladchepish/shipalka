package ru.shipal.test.testproject.pages;

import org.openqa.selenium.WebDriver;
import ru.shipal.test.testproject.pages.front.WebSiteMainPage;

public class Navigation extends BasePage {

    public Navigation(WebDriver driver) {
        super(driver);
    }

    public void goToAdminAuatorizationPage() {
        driver.get("http://localhost/litecart/admin/");
    }

    public WebSiteMainPage goToWebSiteMainPage(){
        driver.get("http://localhost/litecart/");
        return new WebSiteMainPage(driver);
    }

    public void openPageByUrl(String url) {
        driver.get(url);
    }

}
