# JavaChat-MultiCliente

Este proyecto es una aplicación de chat desarrollada en Java que permite la comunicación entre múltiples clientes a través de un servidor central. Utiliza `Sockets` y `Java Swing` para la interfaz gráfica.

## 🧩 Características

- Interfaz gráfica con Java Swing.
- Funciona como cliente o como servidor desde la misma aplicación.
- Soporte para múltiples clientes conectados simultáneamente.
- Cada cliente envía su nombre al servidor.
- El servidor mantiene un historial de mensajes para cada sesión.
- Los nuevos clientes reciben el historial al conectarse.
- El cliente no muestra su propio mensaje dos veces.
- Comentarios detallados en todo el código para fácil aprendizaje.

## 📁 Estructura del proyecto

tareaServidor/
├── ChatGUI.java
├── Cliente.java
├── Servidor.java
├── ManejadorCliente.java


## 🚀 Cómo usar

1. Clona el repositorio:

```bash
git clone https://github.com/ronaldbit/JavaChat-MultiCliente.git
