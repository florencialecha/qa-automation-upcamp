package scripts;


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

    @Parameters({"product1NameParam", "product1PriceParam","product1CurrencyParam"})
    @Test
    public void addToCartProduct1(String product1NameParam, String product1PriceParam, String product1CurrencyParam){
        driver.get("http://opencart.abstracta.us/");
        HomePage homePage = new HomePage(driver);
        homePage.setCurrencyTo(product1CurrencyParam);
        ProductSearchPage productSearchPage = homePage.search(product1NameParam);
        ProductPage productPage = productSearchPage.clickProduct(product1NameParam);
        assertEquals(productPage.getPrice(), product1PriceParam);
        productPage.addToCart();
        CartPage cartPage = productPage.goToCart();
        cartPage.waitForCartToLoad();
        assertEquals(cartPage.getQuantity(), "1");
    }

    @Parameters({"product2NameParam", "product2PriceParam","product2CurrencyParam"})
    @Test
    public void addToCartProduct2(String product2NameParam, String product2PriceParam, String product2CurrencyParam){
        driver.get("http://opencart.abstracta.us/");
        HomePage homePage = new HomePage(driver);
        homePage.setCurrencyTo(product2CurrencyParam);
        ProductSearchPage productSearchPage = homePage.search(product2NameParam);
        ProductPage productPage = productSearchPage.clickProduct(product2NameParam);
        assertEquals(productPage.getPrice(), product2PriceParam);
        productPage.addToCart();
        CartPage cartPage = productPage.goToCart();
        cartPage.waitForCartToLoad();
        assertEquals(cartPage.getQuantity(), "1");
    }

    @AfterSuite
    public void setDown() {
        driver.close();
        System.exit(0);
    }
}
