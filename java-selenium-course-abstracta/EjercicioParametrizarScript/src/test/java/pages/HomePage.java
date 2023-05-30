package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.Keys.ENTER;

public class HomePage {

    private static WebDriver driver;
    @FindBy(name = "search")
    private WebElement searchBar;

    @FindBy(css = "#form-currency .btn-group")
    private WebElement currencyButton;

    public HomePage(WebDriver driver) {
        HomePage.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ProductSearchPage search(String product) {
        searchBar.sendKeys(product);
        searchBar.sendKeys(ENTER);
        return new ProductSearchPage(driver);
    }

    public void setCurrencyTo(String currency) {
        currencyButton.click();
        currencyButton.findElement(By.name(currency)).click();
    }

}
