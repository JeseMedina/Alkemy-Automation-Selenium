package com.alkemy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class HomePage extends BasePage {

    @FindBy(xpath = "//div[@class='inventory_list']//div[@class='inventory_item']")
    private List<WebElement> productos;

    @FindBy(id = "shopping_cart_container")
    private WebElement carritoLink;

    @FindBy(className = "shopping_cart_badge")
    private WebElement carritoBadge;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuBtn;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    @FindBy(className = "product_sort_container")
    private WebElement filtroPrecios;

    @FindBy(className = "inventory_item_price")
    private WebElement primerPrecio;

    @FindBy(linkText = "Twitter")
    private WebElement twitterLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void seleccionarProducto(int indice) {
        if (indice >= 0 && indice < productos.size()) {
            WebElement producto = productos.get(indice);
            producto.click();
        }
    }

    public void seleccionarProductoPorNombre(String nombre) {
        String xpath = "//div[@class='inventory_item']//div[text()='" + nombre + "']/ancestor::div[@class='inventory_item']";
        WebElement producto = driver.findElement(By.xpath(xpath));
        producto.click();
    }

    public void agregarAlCarrito() {
        WebElement boton = driver.findElement(By.xpath("//button[contains(text(), 'Add to cart')]"));
        boton.click();
    }

    public void irAlCarrito() {
        wait.until(ExpectedConditions.elementToBeClickable(carritoLink));
        carritoLink.click();
    }

    public String obtenerTextoCarritoBadge() {
        try {
            return carritoBadge.getText();
        } catch (Exception e) {
            return "0";
        }
    }

    public void aplicarFiltroPrecios(String valor) {
        Select select = new Select(filtroPrecios);
        select.selectByValue(valor);
    }

    public String obtenerPrimerPrecio() {
        wait.until(ExpectedConditions.visibilityOf(primerPrecio));
        return primerPrecio.getText();
    }

    public void clickTwitterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(twitterLink));
        twitterLink.click();
    }

    @Override
    public void logout() {
        menuBtn.click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink));
        logoutLink.click();
    }
}
