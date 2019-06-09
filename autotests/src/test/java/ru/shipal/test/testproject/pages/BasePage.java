package ru.shipal.test.testproject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.shipal.test.testproject.lib.TimeOut;

import java.io.File;
import java.util.*;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait loadingWait;
    protected WebDriverWait shortWait;
    protected WebDriverWait actionWait;
    protected WebDriverWait betWait;
    protected WebDriverWait defaultWait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.shortWait = new WebDriverWait(driver, TimeOut.shortTimeout);
        this.defaultWait = new WebDriverWait(driver, TimeOut.defaultTimeout);
        this.actionWait = new WebDriverWait(driver, TimeOut.action);
        this.loadingWait = new WebDriverWait(driver, TimeOut.loading);
        this.betWait = new WebDriverWait(driver, TimeOut.betTimeout);
    }

    protected WebElement getElement(By by){
        return driver.findElement(by);
    }

    public List<WebElement> getElements(By by){
        try {
            return driver.findElements(by);
        } catch (InvalidSelectorException e) {
            return new ArrayList<WebElement>(); //empty list
        }
    }

    public void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e){
            //
        }
    }

    protected void findAndFeelField(By by, String text){
        clickByElement(by);
        getElement(by).clear();
        getElement(by).sendKeys(text);
    }

    protected void findAndChoseOptionFromSelect(By by, String value){
        clickByElement(by);
        getElement(By.xpath("//li[text()='" + value + "']")).click();
    }

    protected void findAndSelectOptionFromSelect(By by, String value){
        clickByElement(by);
        getElement(by).findElement(By.xpath(".//option[text()='" + value + "']")).click();
    }

    protected void clickByElement(By by){
        getElement(by).click();
    }

    public LinkedList<String> sortListByAlphabet(List<String> notSortedList) {
        LinkedList<String> sortedList = new LinkedList<>();
        for (String item : notSortedList){
            sortedList.add(item);
        }
        sortedList.sort(Comparator.naturalOrder());
        return sortedList;
    }

    public boolean isRegularPriceGrey(String color) {
        if (color != null) {
            String template = color.substring(color.indexOf("(") + 1, color.lastIndexOf(")")).replaceAll(" ", "");
            List<String> listRGB = Arrays.asList(template.split(","));
            String red = listRGB.get(0);
            String green = listRGB.get(1);
            String blue = listRGB.get(2);
            return red.equals(green) && green.equals(blue);
        }
        return false;
    }

    public boolean isComparingPriceColorRed(String color) {
        if (color != null) {
            String template = color.substring(color.indexOf("(") + 1, color.lastIndexOf(")")).replaceAll(" ", "");
            List<String> listRGB = Arrays.asList(template.split(","));
            String green = listRGB.get(1);
            String blue = listRGB.get(2);
            return green.equals("0") && blue.equals("0");
        }
        return false;
    }

    protected void attach(By by, File file) {
        if (file != null) {
            getElement(by).sendKeys(file.getAbsolutePath());
        }
    }

    public void returnOnPreviesPage(){
        driver.navigate().back();
    }
}
