package tests;

import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HealthCheckTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @Test
    public void checkMainPageAccessible() {

        driver.get("http://digitalbank.upcamp.io/bank/login");
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "http://digitalbank.upcamp.io/bank/login";
        String pageErrorMessage = "The main page should be accessible";
        Assert.assertEquals(currentUrl, expectedUrl, pageErrorMessage);

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
