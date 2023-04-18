import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

//Test creado por Katalon
public class OpenCartTest {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private JavascriptExecutor js;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions(); //configuraciones de la nueva versiíon
        options.addArguments("--remote-allow-origins=*"); //configuraciones de la nueva versiíon
        driver = new ChromeDriver(options); //configuraciones de la nueva versiíon
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void testAddToCart() throws Exception {
        driver.get("http://opencart.abstracta.us/");
        driver.manage().window().maximize(); // maximizar la ventana del navegador
        driver.findElement(By.cssSelector("#top .btn-group")).click(); //click en el boton de moneda
        driver.findElement(By.name("USD")).click(); // seleccionar la moneda USD
        driver.findElement(By.xpath("//img[@alt='iPhone']")).click(); // click en un producto
        assertEquals(driver.findElement(By.cssSelector("#content .row .col-sm-4 h2")).getText(), "$123.20"); // verificar el precio del producto
        driver.findElement(By.cssSelector("#top .fa-shopping-cart")).click();
        driver.findElement(By.cssSelector(".table-responsive input.form-control")).click();
        assertEquals(driver.findElement(By.cssSelector(".table-responsive input.form-control [value]")).getAttribute("value"), "1");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
