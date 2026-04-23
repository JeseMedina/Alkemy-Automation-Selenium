package com.alkemy;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver; 
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EcommerceTitleTest {

    WebDriver driver;
    String url = "https://www.saucedemo.com/";
    String tituloEsperado = "Swag Labs";

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Validar título en Edge")
    void testTitleEdge() {
        System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
        driver = new EdgeDriver(); 
        
        driver.get(url);
        assertEquals(tituloEsperado, driver.getTitle());
    }

    @Test
    @DisplayName("Validar título en Firefox")
    void testTitleFirefox() {
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        driver = new FirefoxDriver();
        
        driver.get(url);
        assertEquals(tituloEsperado, driver.getTitle());
    }
}