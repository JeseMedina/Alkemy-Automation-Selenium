package com.alkemy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EcommerceTest {

    WebDriver driver;
    String url = "https://www.saucedemo.com/";
    String tituloEsperado = "Swag Labs";

    String driverStrategy = "manual"; 

    @BeforeEach
    void setUp() {
        if (driverStrategy.equals("manager")) {
            // ESTRATEGIA 1: Driver Manual
            System.out.println("Ejecutando con Estrategia: MANUAL");
            System.setProperty("webdriver.edge.driver", "drivers/msedgedriver.exe");
        } else {
            // ESTRATEGIA 2: WebDriverManager
            System.out.println("Ejecutando con Estrategia: WebDriverManager");
            WebDriverManager.edgedriver().setup();
        }
        
        driver = new EdgeDriver();
    }

    @Test
    @DisplayName("Prueba de validación de título")
    void testValidarTitulo() {
        driver.get(url);
        String tituloActual = driver.getTitle();
        assertEquals(tituloEsperado, tituloActual, "El título no coincide con el esperado");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}