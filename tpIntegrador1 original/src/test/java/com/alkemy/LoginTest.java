package com.alkemy;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {
    WebDriver driver;
    String driverStrategy = "manual";

    @BeforeEach
    void setUp() {
        if (driverStrategy.equals("manual")) {
            System.setProperty("webdriver.edge.driver", "drivers/msedgedriver.exe");
        }
        driver = new EdgeDriver();
    }

    @Test
    void testLoginExitoso() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        
        assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    @AfterEach
    void tearDown() { if (driver != null) driver.quit(); }
}