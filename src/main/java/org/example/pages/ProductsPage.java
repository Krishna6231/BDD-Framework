package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductsPage {
    WebDriver driver;
    WebDriverWait wait;

    public ProductsPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }

    public void addToCart(String productName){
        // Wait for the products container to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));
        System.out.println("inaddtocart");
        List<WebElement> products = driver.findElements(By.className("inventory_item"));
        for(WebElement product : products){
            String name  = product.findElement(By.className("inventory_item_name")).getText();
            if(name.equalsIgnoreCase(productName)){
                WebElement addButton = product.findElement(By.tagName("button"));
                wait.until(ExpectedConditions.elementToBeClickable(addButton));
                addButton.click();
                break;
            }
        }
    }

    public void clickCart(){
        System.out.println("inclickcart");
        WebElement cartLink = wait.until(ExpectedConditions.elementToBeClickable(By.className("shopping_cart_link")));
        cartLink.click();
    }
}
