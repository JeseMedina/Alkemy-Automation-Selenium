package com.alkemy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement campoUsuario;

    @FindBy(id = "password")
    private WebElement campoContraseña;

    @FindBy(id = "login-button")
    private WebElement botonLogin;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement mensajeError;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String usuario, String contraseña) {
        campoUsuario.sendKeys(usuario);
        campoContraseña.sendKeys(contraseña);
        botonLogin.click();
    }

    public String validarMensajeError() {
        wait.until(ExpectedConditions.visibilityOf(mensajeError));
        return mensajeError.getText();
    }

    public boolean loginExitoso() {
        try {
            wait.until(ExpectedConditions.urlContains("inventory.html"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
