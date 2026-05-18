package com.alkemy;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import com.alkemy.pages.LoginPage;
import com.alkemy.pages.HomePage;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SocialMediaTest {
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
    void testTwitterTab() {
        String originalWindow = driver.getWindowHandle();
        homePage.clickTwitterLink();

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