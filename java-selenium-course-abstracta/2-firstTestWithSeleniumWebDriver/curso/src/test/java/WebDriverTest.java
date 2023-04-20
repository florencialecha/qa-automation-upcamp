import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class WebDriverTest {
    WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe"); // configurar el path del driver
        ChromeOptions options = new ChromeOptions(); //configuraciones de la nueva versiíon
        options.addArguments("--remote-allow-origins=*"); //configuraciones de la nueva versiíon
        driver = new ChromeDriver(options); //configuraciones de la nueva versiíon
    }

    @Test
    public void testAddToCart() {
        driver.get("http://opencart.abstracta.us/"); // abrir la url
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // esperar 10 segundos
        driver.manage().window().maximize(); // maximizar la ventana del navegador
        driver.findElement(By.cssSelector("#top .btn-group")).click();
        driver.findElement(By.name("USD")).click();
    }

    @AfterTest // Cerrar el navegador y terminar la ejecución del programa en Java
    public void setDown() {
        driver.close();
        System.exit(0);
    }
}
