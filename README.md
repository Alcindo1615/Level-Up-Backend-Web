# Level-Up Backend Web

Backend en **Spring Boot** para el proyecto **Level-Up Gamer Web**, organizado en microservicios e integrado con **Firestore (Firebase)** como base de datos.  
Expone APIs REST que se consumen desde el frontend en **React**.

---

## 🧩 Microservicios

El proyecto está dividido en los siguientes servicios:

- **usuarioService**  
  Gestión de usuarios del sistema (registro, login, datos de perfil).

- **productoService**  
  Administración de productos/juegos disponibles en la tienda (CRUD de productos).

- **carritoService**  
  Manejo del carrito de compras del usuario (agregar, listar y eliminar ítems).

- **pedidoService**  
  Creación y consulta de pedidos generados a partir del carrito (detalle del pedido, estado, etc.).

- **contactoService**  
  Recepción y almacenamiento de mensajes enviados desde el formulario de contacto del sitio web.

Cada microservicio es una aplicación Spring Boot independiente, con su propia configuración y controladores.

---

## 🏗️ Estructura de carpetas

```bash
LEVEL-UP-BACKEND-WEB/
 ├─ .vscode/              # Configuración de VS Code (opcional)
 ├─ carritoService/       # Microservicio de Carrito
 ├─ contactoService/      # Microservicio de Contacto
 ├─ pedidoService/        # Microservicio de Pedido
 ├─ productoService/      # Microservicio de Producto
 └─ usuarioService/       # Microservicio de Usuario
