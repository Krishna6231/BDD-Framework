package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    @FindBy(id = "user-name")
    WebElement usernameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(xpath = "//input[@id='login-button']")
    WebElement loginButton;

    public LoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public void login(String user, String pass) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // or new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOf(usernameField));
        usernameField.sendKeys(user);

        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys(pass);
        Thread.sleep(5000);
//        driver.findElement(By.xpath("//input[@id=\"login-button\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }
}
