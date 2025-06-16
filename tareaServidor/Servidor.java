package tareaServidor; // Declaración del paquete

import java.io.*; // Importación de librerías
import java.net.*; // Importación de librerías
import java.util.*; // Importación de librerías
import java.util.concurrent.CopyOnWriteArrayList; // Importación de librerías

public class Servidor { // Definición de la clase principal
    private ServerSocket sServidor; // Declaración de socket
    private final List<Socket> clientes = new CopyOnWriteArrayList<>(); // Declaración de socket
    private final ChatGUI gui; // Referencia a la interfaz gráfica
    private final int puerto;
    private final List<String> historialMensajes = new ArrayList<>(); // Declaración de lista

    public Servidor(int p, ChatGUI gui) { // Referencia a la interfaz gráfica
        this.puerto = p;
        this.gui = gui;
    }

    public void iniciar() {
        try {
            sServidor = new ServerSocket(puerto); // Crear un servidor de sockets
            gui.appendMensaje("[Sistema] Servidor escuchando en puerto " + puerto);
            while (true) {
                Socket sCliente = sServidor.accept(); // Aceptar conexión entrante
                clientes.add(sCliente);
                new Thread(new ManejadorCliente(sCliente, gui, clientes, this)).start();
            }
        } catch (IOException e) {
            gui.appendMensaje("[Error] " + e.getMessage());
        }
    }

    public void agregarHistorial(String mensaje) {
        historialMensajes.add(mensaje);
    }

    public List<String> getHistorialMensajes() { // Declaración de lista
        return historialMensajes;
    }
}