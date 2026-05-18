package com.alkemy;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import com.alkemy.pages.LoginPage;
import com.alkemy.pages.HomePage;
import com.alkemy.pages.CartPage;
import com.alkemy.pages.CheckoutPage;
import static org.junit.jupiter.api.Assertions.*;

public class FlujoCajeroTest {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.edge.driver", "drivers/msedgedriver.exe");
        driver = new EdgeDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);

        loginPage.navegar("https://www.saucedemo.com/");
    }

    @Test
    @DisplayName("Flujo completo de compra: Login -> Seleccionar producto -> Carrito -> Checkout")
    void testFlujoCompletaDeCompra() {
        // Login exitoso
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(loginPage.loginExitoso());

        // Agregar producto al carrito
        homePage.agregarAlCarrito();
        assertEquals("1", homePage.obtenerTextoCarritoBadge());

        // Verificar producto en carrito
        homePage.irAlCarrito();
        assertTrue(cartPage.verificarProductoEnCarrito("Sauce Labs Backpack"));
        assertEquals(1, cartPage.obtenerCantidadProductos());

        // Checkout
        cartPage.irACheckout();
        checkoutPage.completarDatos("Juan", "Pérez", "1425");
        checkoutPage.finalizarCompra();

        // Validar compra exitosa
        assertTrue(checkoutPage.compraExitosa());
    }

    @Test
    @DisplayName("Login exitoso redirige a página de inventario")
    void testLoginExitoso() {
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(loginPage.loginExitoso());
    }

    @Test
    @DisplayName("Verificar que agregar producto actualiza el carrito")
    void testAgregarProductoAlCarrito() {
        loginPage.login("standard_user", "secret_sauce");

        homePage.agregarAlCarrito();
        assertEquals("1", homePage.obtenerTextoCarritoBadge());
    }

    @Test
    @DisplayName("Filtro de precios: Menor a Mayor")
    void testFiltroMenorAMayor() {
        loginPage.login("standard_user", "secret_sauce");

        homePage.aplicarFiltroPrecios("lohi");
        assertTrue(homePage.obtenerPrimerPrecio().contains("$"));
    }

    @AfterEach
    void tearDown() { if (driver != null) driver.quit(); }
}
