package com.alkemy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {

    @FindBy(id = "first-name")
    private WebElement campoNombre;

    @FindBy(id = "last-name")
    private WebElement campoApellido;

    @FindBy(id = "postal-code")
    private WebElement campoCodigoPostal;

    @FindBy(id = "continue")
    private WebElement botonContinuar;

    @FindBy(id = "finish")
    private WebElement botonFinalizarCompra;

    @FindBy(className = "complete-header")
    private WebElement mensajeExito;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void completarDatos(String nombre, String apellido, String codigoPostal) {
        campoNombre.sendKeys(nombre);
        campoApellido.sendKeys(apellido);
        campoCodigoPostal.sendKeys(codigoPostal);
        botonContinuar.click();
    }

    public void finalizarCompra() {
        wait.until(ExpectedConditions.elementToBeClickable(botonFinalizarCompra));
        botonFinalizarCompra.click();
    }

    public boolean compraExitosa() {
        try {
            wait.until(ExpectedConditions.visibilityOf(mensajeExito));
            return mensajeExito.getText().contains("Thank you");
        } catch (Exception e) {
            return false;
        }
    }

    public String obtenerMensajeExito() {
        wait.until(ExpectedConditions.visibilityOf(mensajeExito));
        return mensajeExito.getText();
    }
}
