package com.alkemy;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutTest {
    WebDriver driver;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.edge.driver", "drivers/msedgedriver.exe");
        driver = new EdgeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @Test
    void testCompraCompleta() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        
        driver.findElement(By.id("first-name")).sendKeys("QA");
        driver.findElement(By.id("last-name")).sendKeys("Tester");
        driver.findElement(By.id("postal-code")).sendKeys("1234");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();
        
        String mensaje = driver.findElement(By.className("complete-header")).getText();
        assertEquals("Thank you for your order!", mensaje);
    }

    @AfterEach
    void tearDown() { if (driver != null) driver.quit(); }
}