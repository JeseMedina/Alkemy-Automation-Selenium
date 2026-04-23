package com.alkemy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EcommerceTest {

    WebDriver driver;
    String url = "https://www.saucedemo.com/";
    String tituloEsperado = "Swag Labs";

    String driverStrategy = "manual"; 

    @BeforeEach
    void setUp() {
        if (driverStrategy.equals("manual")) {
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

    @Test
    @DisplayName("Identificación de elementos del Login")
    void localizarElementosLogin() {
        driver.get("https://www.saucedemo.com/");
        
        // 1. Localización del campo 'Username' por ID
        WebElement txtUsername = driver.findElement(By.id("user-name"));
        
        // 2. Localización del campo 'Password' por Name (cumple con usar 2 tipos de locators)
        WebElement txtPassword = driver.findElement(By.name("password"));
        
        // 3. Localización del botón 'Login' por ID
        WebElement btnLogin = driver.findElement(By.id("login-button"));

        // Verificación simple para confirmar que se encontraron
        assertTrue(txtUsername.isDisplayed(), "El campo usuario no es visible");
        assertTrue(txtPassword.isDisplayed(), "El campo contraseña no es visible");
        assertTrue(btnLogin.isDisplayed(), "El botón de login no es visible");
    }

    @Test
    @DisplayName("Flujo de Login y Filtro")
    void interactuarConElementos() {
        driver.get("https://www.saucedemo.com/");

        // 1. Escribir en un input (simula ingreso de datos)
        // Tipo de elemento: Input de texto | Método: sendKeys()
        WebElement txtUsername = driver.findElement(By.id("user-name"));
        txtUsername.sendKeys("standard_user"); // Simula al usuario escribiendo su nombre

        WebElement txtPassword = driver.findElement(By.name("password"));
        txtPassword.sendKeys("secret_sauce"); // Simula al usuario escribiendo su contraseña

        // 2. Hacer clic en un botón (simula envío de formulario)
        // Tipo de elemento: Botón | Método: click()
        WebElement btnLogin = driver.findElement(By.id("login-button"));
        btnLogin.click(); // Simula al usuario presionando 'Login'

        // 3. Seleccionar opción de un dropdown (simula cambio de vista)
        // Tipo de elemento: Select| Método: Select (clase de soporte)
        WebElement dropDownFiltro = driver.findElement(By.className("product_sort_container"));
        Select selectFiltro = new Select(dropDownFiltro);
        
        // Seleccionamos por texto visible: "Price (low to high)"
        selectFiltro.selectByValue("lohi"); // Simula al usuario cambiando el orden de productos
        
        // Verificación final
        WebElement labelProductos = driver.findElement(By.className("title"));
        assertTrue(labelProductos.isDisplayed(), "El login falló o no se cargó la página de productos");
    }
}