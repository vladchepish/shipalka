package ru.shipal.test.testproject.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ru.shipal.test.testproject.pages.BasePage;

public class TestBase {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeClass
    public void start(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
        BasePage basePage = new BasePage(driver);
    }

    @AfterClass
    public void stop(){
        driver.quit();
        driver = null;
    }
}
