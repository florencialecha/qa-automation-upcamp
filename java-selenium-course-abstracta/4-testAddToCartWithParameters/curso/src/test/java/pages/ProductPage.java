package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

    private static WebDriver driver;
    @FindBy(css = "#content .row .col-sm-4 h2")
    private WebElement price;
    @FindBy(id = "button-cart")
    private WebElement addToCartButton;
    @FindBy(css = "#top .fa-shopping-cart")
    private WebElement shoppingCartButton;

    public ProductPage(WebDriver driver) {
        ProductPage.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPrice() {
        return price.getText();
    }

    public void addToCart() {
        addToCartButton.click();
    }

    public CartPage goToCart() {
        shoppingCartButton.click();
        return new CartPage(driver);
    }
}
