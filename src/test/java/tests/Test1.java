package tests;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CheckPage;
import pages.Homepage;
import pages.LoginPage;

@Epic("SauceDemo Tests")
@Feature("User")
public class Test1 extends BaseTest {
    @DataProvider(name = "loginCredentials")
    public Object[][] getLoginCredentials() {
        // Define credential sets: {username, password, expectedResult}
        return new Object[][]{
                {"standard_user", "secret_sauce", true},  // Valid credentials
                {"locked_out_user", "secret_sauce", false},  // Locked out user
                {"problem_user", "secret_sauce", true},  // Problem user (valid but may have UI issues)
                {"performance_glitch_user", "secret_sauce", true},  // Performance glitch user (valid)
                {"invalid_user", "secret_sauce", false},  // Invalid username
                {"standard_user", "wrong_password", false},  // Wrong password
                {"", "secret_sauce", false},  // Empty username
                {"standard_user", "", false}  // Empty password
        };
    }


    @Test(dataProvider = "loginCredentials", description = "Login with multiple credential sets")
    @Story("Login User")
    @Description("Login with multiple credential sets")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginWithCredentials(String username, String password, boolean expectedSuccess) throws NullPointerException{
        // Enter credentials
        LoginPage loginPage = new LoginPage(driver);
        loginPage.Login(username,password);

        // Validate the result
        if (expectedSuccess) {
            // Check if login was successful (redirect to inventory page)
            Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"),
                    "Login failed for valid credentials: " + username);
        } else {
            // Check if error message is displayed
            WebElement errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']"));
            Assert.assertTrue(errorMessage.isDisplayed(),
                    "Error message not displayed for invalid credentials: " + username);
        }
    }



}
