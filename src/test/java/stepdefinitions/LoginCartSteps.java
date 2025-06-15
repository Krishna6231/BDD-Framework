package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.CartPage;
import org.example.pages.LoginPage;
import org.example.pages.ProductsPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertTrue;


public class LoginCartSteps {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;

    @Given("I am on the SauceDemo login page")
    public void i_am_on_the_login_page() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();            // ✅ Instantiate actual browser
        driver.manage().window().maximize();     // Optional: Maximize window
        driver.get("https://www.saucedemo.com/");

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
    }
    @When("I login with username {string} and password {string}")
    public void i_login_with_credentials(String username, String password) throws InterruptedException {
        loginPage.login(username,password);
    }
    @When("I add the product {string} to the cart")
    public void i_add_product_to_cart(String productName){
        productsPage.addToCart(productName);
    }
    @When("I go to cart page")
    public void i_go_to_the_cart_page(){
        productsPage.clickCart();
    }
    @Then("I should see {string} in the cart")
    public void i_should_see_product_in_the_cart(String expectedProduct){
        Assert.assertTrue(cartPage.isProductInCart(expectedProduct));
        driver.quit();
    }

}
