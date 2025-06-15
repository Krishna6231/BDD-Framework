package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {
    WebDriver driver;
    WebDriverWait wait;

    public CartPage(WebDriver driver){
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }

    public boolean isProductInCart(String productName){
        // Wait for the cart list to appear
        System.out.println("isproductincart");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart_list")));

        List<WebElement> cartItems = driver.findElements(By.className("inventory_item_name"));
        for(WebElement item : cartItems){
            if(item.getText().equalsIgnoreCase(productName)){
                return true;
            }
        }
        return false;
    }
}
