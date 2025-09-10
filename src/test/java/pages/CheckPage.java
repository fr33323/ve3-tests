package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CheckPage {
    WebDriver driver;

    @FindBy(xpath = "//input[@id='first-name']")
    WebElement firstName;

    @FindBy(xpath = "//input[@id='last-name']")
    WebElement lastName;

    @FindBy(xpath = "//input[@id='postal-code']")
    WebElement postCode;

    @FindBy(xpath = "//input[@value='CONTINUE']")
    WebElement continueBtn;

    @FindBy(xpath = "//a[normalize-space()='FINISH']")
    WebElement finishOrderBtn;

    @FindBy(xpath = "//h2[normalize-space()='THANK YOU FOR YOUR ORDER']")
    WebElement orderConfirm;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[3]/div[1]/div[2]/a[2]")
    WebElement checkoutBtn;

    public CheckPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void Order(String fName, String lName, String pCode){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        new Homepage(driver).getCartIcon().click();

        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn));
        checkoutBtn.click();

        wait.until(ExpectedConditions.visibilityOf(continueBtn));
        firstName.sendKeys(fName);
        lastName.sendKeys(lName);
        postCode.sendKeys(pCode);
        System.out.println("First name: " + fName + "Last name: " + lName + "Post Code: " + pCode);
        continueBtn.click();

        wait.until(ExpectedConditions.visibilityOf(finishOrderBtn));
        finishOrderBtn.click();

        wait.until(ExpectedConditions.visibilityOf(orderConfirm));
        Assert.assertEquals(orderConfirm.getText(), "THANK YOU FOR YOUR ORDER");
    }
}
