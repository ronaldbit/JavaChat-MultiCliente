package tareaServidor; // Declaración del paquete al que pertenece esta clase

import java.io.*; // Importación de clases para entrada/salida
import java.net.*; // Importación para sockets y direcciones de red
import java.util.*; // Importación para utilidades como Scanner

public class Cliente { // Definición de la clase Cliente
    private Socket socket; // Socket para conectarse al servidor
    private Scanner entrada; // Scanner para leer los datos entrantes del servidor
    private PrintStream salida; // Stream para enviar datos al servidor
    private String host; // Dirección IP del servidor
    private int puerto; // Puerto al que se conecta el cliente
    private String nombre; // Nombre de usuario del cliente
    private ChatGUI gui; // Referencia a la interfaz gráfica

    public Cliente(String host, int puerto, String nombre, ChatGUI gui) { // Constructor del cliente
        this.host = host;
        this.puerto = puerto;
        this.nombre = nombre;
        this.gui = gui;
    }

    public void iniciar() { // Método que inicia la conexión con el servidor
        try {
            socket = new Socket(host, puerto); // Se conecta al servidor
            gui.appendMensaje("[Sistema] Conectado al servidor: " + host + ":" + puerto);

            entrada = new Scanner(socket.getInputStream()); // Prepara para leer datos del servidor
            salida = new PrintStream(socket.getOutputStream()); // Prepara para enviar datos al servidor

            salida.println(nombre); // Envia el nombre al servidor

            while (entrada.hasNextLine()) { // Escucha mensajes del servidor
                String mensaje = entrada.nextLine(); // Lee un mensaje
                gui.appendMensaje(mensaje); // Mostrarlo
            }
            /**
            while (entrada.hasNextLine()) {
                String mensaje = entrada.nextLine();
                String identificadorPropio = nombre + " (" + obtenerIPLocal() + "):";

                if (!mensaje.startsWith(identificadorPropio)) {
                    gui.appendMensaje(mensaje);
                }
            }**/

        } catch (IOException e) {
            gui.appendMensaje("[Error] No se pudo conectar: " + e.getMessage());
        }
    }

    public void enviar(String mensaje) { // Envia un mensaje al servidor
        if (salida != null) {
            salida.println(mensaje);
        }
    }

    private String obtenerIPLocal() { // Retorna la IP de esta máquina
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "localhost";
        }
    }
}

