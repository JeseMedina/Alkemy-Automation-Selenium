# E-commerce Automation - Entrega 2: Page Object Model (POM)

## 📋 Estructura del Proyecto

```
src/
├── main/java/com/alkemy/
│   └── pages/
│       ├── BasePage.java          # Clase base con funcionalidades comunes
│       ├── LoginPage.java         # Page Object para login
│       ├── HomePage.java          # Page Object para página de productos
│       ├── CartPage.java          # Page Object para carrito
│       └── CheckoutPage.java      # Page Object para checkout
└── test/java/com/alkemy/
    ├── LoginTest.java             # Test de login (refactorizado)
    ├── LogoutTest.java            # Test de logout (refactorizado)
    ├── CheckoutTest.java          # Test de checkout completo (refactorizado)
    ├── FilterTest.java            # Test de filtros (refactorizado)
    ├── SocialMediaTest.java       # Test de redes sociales (refactorizado)
    └── FlujoCajeroTest.java       # Test del flujo completo (NUEVO)
```

## 🎯 Page Objects Implementados

### 1. BasePage
- Clase base que todas las páginas heredan
- Inicialización con Page Factory
- WebDriverWait para esperas implícitas
- Métodos comunes:
  - `navegar(url)` - Navegar a una URL
  - `obtenerUrlActual()` - Obtener URL actual
  - `obtenerTitulo()` - Obtener título de página
  - `logout()` - Logout (sobrescribible)

### 2. LoginPage
**Elementos:**
- `campoUsuario` - Campo usuario
- `campoContraseña` - Campo contraseña
- `botonLogin` - Botón login
- `mensajeError` - Mensaje de error

**Métodos:**
- `login(usuario, contraseña)` - Ejecutar login
- `loginExitoso()` - Validar login exitoso
- `validarMensajeError()` - Obtener mensaje de error

### 3. HomePage
**Elementos:**
- `productos` - Lista de productos
- `carritoLink` - Link del carrito
- `carritoBadge` - Badge con cantidad de items
- `filtroPrecios` - Dropdown de filtros
- `twitterLink` - Link de Twitter

**Métodos:**
- `seleccionarProducto(indice)` - Seleccionar producto por índice
- `agregarAlCarrito()` - Agregar producto al carrito
- `irAlCarrito()` - Navegar al carrito
- `obtenerTextoCarritoBadge()` - Obtener cantidad de items
- `aplicarFiltroPrecios(valor)` - Aplicar filtro de precios
- `obtenerPrimerPrecio()` - Obtener primer precio mostrado
- `logout()` - Cerrar sesión

### 4. CartPage
**Elementos:**
- `itemsCarrito` - Items del carrito
- `botonCheckout` - Botón para ir a checkout
- `cantidades` - Cantidades de cada producto

**Métodos:**
- `verificarProductoEnCarrito(nombre)` - Verificar producto en carrito
- `obtenerCantidadProductos()` - Obtener cantidad total de items
- `irACheckout()` - Navegar a checkout

### 5. CheckoutPage
**Elementos:**
- `campoNombre` - Campo nombre
- `campoApellido` - Campo apellido
- `campoCodigoPostal` - Campo código postal
- `botonContinuar` - Botón continuar
- `botonFinalizarCompra` - Botón finalizar compra
- `mensajeExito` - Mensaje de éxito

**Métodos:**
- `completarDatos(nombre, apellido, codigoPostal)` - Completar datos y continuar
- `finalizarCompra()` - Finalizar compra
- `compraExitosa()` - Validar compra exitosa
- `obtenerMensajeExito()` - Obtener mensaje de éxito

## 📝 Tests Implementados

### Tests Refactorizados
1. **LoginTest** - Valida login exitoso con credenciales correctas
2. **LogoutTest** - Valida logout exitoso desde la página de inicio
3. **CheckoutTest** - Valida flujo de compra completo
4. **FilterTest** - Valida aplicación de filtros de precio
5. **SocialMediaTest** - Valida enlaces de redes sociales en nuevas pestañas

### Test Nuevo
6. **FlujoCajeroTest** - Valida el flujo completo de e-commerce:
   - Login exitoso
   - Selección de producto
   - Agregación al carrito
   - Verificación en carrito
   - Checkout con datos personales
   - Finalización de compra

## ✨ Características Implementadas

### Page Factory
- Uso de anotaciones `@FindBy` para localizar elementos
- Inicialización automática de elementos en BasePage
- Mejora de mantenibilidad del código

### WebDriverWait
- Esperas explícitas de 10 segundos
- Condiciones de visibilidad y clickabilidad
- Manejo de elementos dinámicos

### Buenas Prácticas
- Separación de responsabilidades
- Reutilización de código
- Métodos descriptivos y en español
- Localización de elementos centralizada
- Tests independientes y reutilizables

## 🚀 Ejecución de Tests

```bash
# Compilar el proyecto
mvn clean compile

# Ejecutar todos los tests
mvn test

# Ejecutar test específico
mvn test -Dtest=LoginTest
```

## 📊 Beneficios de POM

1. **Mantenibilidad**: Los cambios en la UI se hacen en un solo lugar
2. **Reutilización**: Los métodos pueden usarse en múltiples tests
3. **Legibilidad**: Los tests son más claros y fáciles de entender
4. **Escalabilidad**: Fácil agregar nuevos tests y páginas
5. **Reducción de duplicidad**: Elimina código repetido

