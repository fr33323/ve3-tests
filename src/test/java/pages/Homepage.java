package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Homepage {
    WebDriver driver;

    @FindBy(css = ".product_sort_container")
    WebElement sortDropdown;

    @FindBy(className = "inventory_item_name")
    List<WebElement> productList;

    @FindBy(css = ".fa-layers-counter.shopping_cart_badge")
    WebElement cartCountIcon;

    @FindBy(xpath = "//a[@class='shopping_cart_link fa-layers fa-fw']")
    WebElement cartIcon;

    public Homepage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String sortFunc(String sort){
        Select sorting = new Select(sortDropdown);
        sorting.selectByValue(sort);
        return sorting.getFirstSelectedOption().getText();
    }

    public void getProds(){
        for (WebElement prodText: productList){
            System.out.println(prodText.getText());
        }
    }

    public String getCartCount(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(cartCountIcon));
        String cartCount = cartCountIcon.getText();
        return cartCount;
    }

    public WebElement getCartIcon(){
        return cartIcon;
    }
}
