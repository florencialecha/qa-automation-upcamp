package dataProviders;

import org.testng.annotations.DataProvider;

public class ProductsData {
    @DataProvider(name = "products")
    public Object[][] getProductsData() {
        return new Object[][]{
                {"MacBook", "$602.00", "USD"},
                {"iPhone", "96.66€", "EUR"},
        };
    }
}