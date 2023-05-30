package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private static WebDriver driver;

    @FindBy(xpath = "//*[@id='content']/form/div/table/tbody/tr/td[4]/div/input")
    private WebElement quantityProduct;

    @FindBy(id = "content")
    private WebElement content;

    @FindBy(className = "table-responsive")
    private WebElement tableCartItems;

    public CartPage(WebDriver driver) {
        CartPage.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getQuantity() {
        String quantity = quantityProduct.getAttribute("value");
        return quantity;
    }

    public boolean isLoaded() {
        return content.isDisplayed();
    }

    public boolean isTableCartItemsLoaded() {
        return tableCartItems.isDisplayed();
    }

    public void waitForCartToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(content));
        wait.until(ExpectedConditions.visibilityOf(tableCartItems));
    }

}
