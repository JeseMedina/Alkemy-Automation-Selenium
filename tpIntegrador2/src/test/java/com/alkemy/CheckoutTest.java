package com.alkemy;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import com.alkemy.pages.LoginPage;
import com.alkemy.pages.HomePage;
import com.alkemy.pages.CartPage;
import com.alkemy.pages.CheckoutPage;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutTest {
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
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    void testCompraCompleta() {
        homePage.agregarAlCarrito();
        homePage.irAlCarrito();
        cartPage.irACheckout();

        checkoutPage.completarDatos("QA", "Tester", "1234");
        checkoutPage.finalizarCompra();

        assertTrue(checkoutPage.compraExitosa());
        assertEquals("Thank you for your order!", checkoutPage.obtenerMensajeExito());
    }

    @Test
    void testAgregarProductoAlCarrito() {
        homePage.agregarAlCarrito();
        assertEquals("1", homePage.obtenerTextoCarritoBadge());
    }

    @AfterEach
    void tearDown() { if (driver != null) driver.quit(); }
}