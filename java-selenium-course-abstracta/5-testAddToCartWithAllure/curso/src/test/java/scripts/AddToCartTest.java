package scripts;

import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;
import pages.ProductSearchPage;

import static org.testng.AssertJUnit.assertEquals;

public class AddToCartTest {

    WebDriver driver;

    @BeforeSuite
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @Description("Add to cart product")
    @Test(dataProvider = "products", dataProviderClass = dataProviders.ProductsData.class)
    public void addToCartProduct(String productName, String productPrice, String currency) {
        driver.get("http://opencart.abstracta.us/");
        HomePage homePage = new HomePage(driver);
        homePage.setCurrencyTo(currency);
        ProductSearchPage productSearchPage = homePage.search(productName);
        ProductPage productPage = productSearchPage.clickProduct(productName);
        assertEquals(productPage.getPrice(), productPrice);
        productPage.addToCart();
        CartPage cartPage = productPage.goToCart();
        cartPage.waitForCartToLoad();

        //todo: improve validiation with dynimic items on table cart
        assertEquals(cartPage.getQuantity(), "1");
    }

    @AfterSuite
    public void setDown() {
        driver.close();
        System.exit(0);
    }
}
