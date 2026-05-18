package com.alkemy;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import com.alkemy.pages.LoginPage;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.edge.driver", "drivers/msedgedriver.exe");
        driver = new EdgeDriver();
        loginPage = new LoginPage(driver);
    }

    @Test
    void testLoginExitoso() {
        loginPage.navegar("https://www.saucedemo.com/");
        loginPage.login("standard_user", "secret_sauce");

        assertTrue(loginPage.loginExitoso());
    }

    @AfterEach
    void tearDown() { if (driver != null) driver.quit(); }
}