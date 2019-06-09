package ru.shipal.test.testproject.tests.admin;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.shipal.test.testproject.objects.Good;
import ru.shipal.test.testproject.pages.Navigation;
import ru.shipal.test.testproject.pages.admin.AdminAddNewProductPage;
import ru.shipal.test.testproject.pages.admin.AdminCatalogPage;
import ru.shipal.test.testproject.pages.admin.AdminLoginPage;
import ru.shipal.test.testproject.pages.admin.AdminMainPage;
import ru.shipal.test.testproject.tests.TestBase;

import java.io.File;

import static org.testng.Assert.assertTrue;
import static ru.shipal.test.testproject.utils.DataGenerator.generateString;
import static ru.shipal.test.testproject.utils.DataGenerator.geniratorNumeric;

public class CreateNewProductTest extends TestBase {

    private static AdminLoginPage loginPage;
    private static Navigation navigation;
    private static AdminMainPage mainPage;
    private static AdminCatalogPage catalogPage;
    private static AdminAddNewProductPage addNewProduct;

    @BeforeMethod
    public void openPageMethod(){
        loginPage = new AdminLoginPage(driver);
        navigation = new Navigation(driver);
        navigation.goToAdminAuatorizationPage();
        mainPage = loginPage.login("admin", "admin");
    }

    @Test
    public void createNewProductTest(){
        File photo = new File("src/test/resources/pic.jpg");
        Good product = new Good().setName(generateString(6)).setCode(geniratorNumeric(4)).setPhoto(photo)
                .setDescription(generateString(50)).setRegularPrice(geniratorNumeric(2));
        catalogPage = mainPage.goToCatalogPage();
        int numberProductsBefore = catalogPage.getProductsNumber();
        addNewProduct = catalogPage.clickAddNewProductBtn();
        addNewProduct.createNewProduct(product);
        int numberProductsAfter = catalogPage.getProductsNumber();
        assertTrue(numberProductsAfter == (numberProductsBefore + 1),
                "После создания товара количество изменилось неверно");
    }
}
