package com.alkemy;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import com.alkemy.pages.LoginPage;
import com.alkemy.pages.HomePage;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogoutTest {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.edge.driver", "drivers/msedgedriver.exe");
        driver = new EdgeDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);

        loginPage.navegar("https://www.saucedemo.com/");
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    void testLogout() {
        homePage.logout();
        assertTrue(loginPage.obtenerUrlActual().contains("saucedemo.com"));
    }

    @AfterEach
    void tearDown() { if (driver != null) driver.quit(); }
}