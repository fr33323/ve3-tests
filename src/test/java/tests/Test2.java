package tests;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckPage;
import pages.Homepage;
import pages.LoginPage;

import java.io.IOException;

public class Test2 extends BaseTest{

    @Test(description = "Order and Checkout")
    @Story("Sort,Order and Checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Description("""
            1. Sort Products by Price (low to high)
            2. Add a product to cart
            3. Check if product successfully added to cart
            4. Complete order checkout process
            """)
    public void orderProd() throws IOException{
        testLowHigh();
        testCart();
        testCheckout();
    }

    @Step("Sort products")
    public void testLowHigh() {
        // Enter credentials
        LoginPage loginPage = new LoginPage(driver);
        loginPage.Login("standard_user", "secret_sauce");

        Homepage homepage = new Homepage(driver);

        String selectedOption = homepage.sortFunc("lohi");
        Assert.assertEquals("Price (low to high)", selectedOption);

        System.out.println("Filtered items list (Price - Low to High): ");
        homepage.getProds();
    }

    @Step("Check cart functionality")
    public void testCart() {

        WebElement addCartBtn = driver.findElement(By.xpath("(//button[@class='btn_primary btn_inventory'][normalize-space()='ADD TO CART'])[1]"));
        addCartBtn.click();

        Homepage homepage = new Homepage(driver);

        String cartCounter = homepage.getCartCount();
        Assert.assertTrue(cartCounter.equalsIgnoreCase("1"), "Item not added to cart!");
        System.out.println("Items in cart: " + cartCounter);
    }

    @Step("Complete checkout & verify")
    public void testCheckout() {

        CheckPage checkPage = new CheckPage(driver);
        checkPage.Order("abc", "def", "123456");
    }
}
