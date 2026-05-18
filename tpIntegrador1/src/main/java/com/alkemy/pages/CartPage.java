package com.alkemy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class CartPage extends BasePage {

    @FindBy(xpath = "//div[@class='cart_item']")
    private List<WebElement> itemsCarrito;

    @FindBy(id = "checkout")
    private WebElement botonCheckout;

    @FindBy(className = "cart_quantity")
    private List<WebElement> cantidades;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean verificarProductoEnCarrito(String nombreProducto) {
        try {
            String xpath = "//div[@class='cart_item']//div[contains(text(), '" + nombreProducto + "')]";
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int obtenerCantidadProductos() {
        return itemsCarrito.size();
    }

    public void irACheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(botonCheckout));
        botonCheckout.click();
    }

    public List<WebElement> obtenerItemsCarrito() {
        return itemsCarrito;
    }
}
