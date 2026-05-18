package com.alkemy;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterTest {
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
    void testFiltroPrecios() {
        Select filter = new Select(driver.findElement(By.className("product_sort_container")));
        filter.selectByValue("lohi");
        
        String primerPrecio = driver.findElement(By.className("inventory_item_price")).getText();
        assertTrue(primerPrecio.contains("7.99")); // El más barato
    }

    @AfterEach
    void tearDown() { if (driver != null) driver.quit(); }
}