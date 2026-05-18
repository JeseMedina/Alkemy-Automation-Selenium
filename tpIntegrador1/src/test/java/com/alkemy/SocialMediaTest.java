package com.alkemy;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SocialMediaTest {
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
    void testTwitterTab() {
        String originalWindow = driver.getWindowHandle();
        driver.findElement(By.linkText("Twitter")).click();

        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        
        assertTrue(driver.getCurrentUrl().contains("x.com") || driver.getCurrentUrl().contains("twitter.com"));
        driver.close();
        driver.switchTo().window(originalWindow);
    }

    @AfterEach
    void tearDown() { if (driver != null) driver.quit(); }
}