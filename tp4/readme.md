Informe de Entrega - TP4: Arquitectura de Selenium
Alumno: Jesé Medina

1. Arquitectura de Selenium implementada
Se diseñó un framework conﬁgurable que alterna entre dos estrategias de gestión de drivers:

Estrategia Manual: Utiliza System.setProperty apuntando al binario ubicado en la carpeta /drivers. Esto demuestra el control directo sobre el ejecutable del navegador.

Estrategia WebDriverManager: Automatiza la descarga y configuración del driver, ideal para entornos de Integración Continua.

2. Respuestas a objetivos teóricos
Rol del WebDriver: Actúa como un puente (intermediario) entre nuestro código en Java y el navegador. Recibe comandos vía HTTP y los traduce en acciones que el navegador entiende.

Comunicación: Selenium utiliza el protocolo W3C para enviar instrucciones al Driver (.exe), el cual controla el navegador mediante APIs nativas.

3. Datos de ejecución
URL validada: https://www.saucedemo.com/

Navegador utilizado: Microsoft Edge (Chromium).

Variable de configuración: driverStrategy (Valores aceptados: "manual" / "manager").