package tests;

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

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @Parameters({"productName", "productPrice","productCurrency"})
    @Test
    public void addToCartProduct(String productName, String productPrice, String productCurrency) {
        driver.get("http://opencart.abstracta.us/");
        HomePage homePage = new HomePage(driver);
        homePage.setCurrencyTo(productCurrency);
        ProductSearchPage productSearchPage = homePage.search(productName);
        ProductPage productPage = productSearchPage.clickProduct(productName);
        assertEquals(productPage.getPrice(), productPrice);
        productPage.addToCart();
        CartPage cartPage = productPage.goToCart();
        cartPage.waitForCartToLoad();
        assertEquals(cartPage.getQuantity(), "1");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
