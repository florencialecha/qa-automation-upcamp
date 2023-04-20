package scripts;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
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

    @Test
    public void addToCart(){
        driver.get("http://opencart.abstracta.us/");
        HomePage homePage = new HomePage(driver);
        homePage.setCurrencyToUSD();
        ProductSearchPage productSearchPage = homePage.search("MacBook");
        ProductPage productPage = productSearchPage.clickProduct("MacBook");
        assertEquals(productPage.getPrice(), "$602.00");
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
