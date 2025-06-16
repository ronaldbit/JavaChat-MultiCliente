package tareaServidor; // Declaración del paquete

import java.io.*; // Importación de librerías
import java.net.*; // Importación de librerías
import java.util.*; // Importación de librerías
import java.util.concurrent.CopyOnWriteArrayList; // Importación de librerías

public class ManejadorCliente implements Runnable { // Definición de la clase principal
    private Socket socket; // Declaración de socket
    private ChatGUI gui; // Referencia a la interfaz gráfica
    private List<Socket> clientes; // Declaración de socket
    private Servidor servidor;
    private Scanner entrada; // Declaración de lector de datos
    private PrintStream salida; // Declaración de stream de salida
    private String nombre;
    private String ip;

    public ManejadorCliente(Socket socket, ChatGUI gui, List<Socket> clientes, Servidor servidor) { // Declaración de socket
        this.socket = socket;
        this.gui = gui;
        this.clientes = clientes;
        this.servidor = servidor;
    }

    @Override
    public void run() {
        try {
            entrada = new Scanner(socket.getInputStream()); // Obtener flujo de entrada
            salida = new PrintStream(socket.getOutputStream()); // Obtener flujo de salida
            nombre = entrada.nextLine(); // El primer mensaje es el nombre
            ip = socket.getInetAddress().getHostAddress();

            gui.appendMensaje("[Sistema] " + nombre + " (" + ip + ") se ha conectado.");

            // Enviar historial
            for (String msg : servidor.getHistorialMensajes()) {
                salida.println(msg);
            }

            while (entrada.hasNextLine()) {
                String mensaje = entrada.nextLine();
                String completo = nombre + " (" + ip + "): " + mensaje;
                gui.appendMensaje(completo);
                servidor.agregarHistorial(completo);
                enviarATodos(completo);
            }

        } catch (IOException e) {
            gui.appendMensaje("[Error] Cliente desconectado: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException ignored) {}
            clientes.remove(socket);
        }
    }

    private void enviarATodos(String mensaje) {
        for (Socket cliente : clientes) {
            try {
                new PrintStream(cliente.getOutputStream()).println(mensaje); // Obtener flujo de salida
            } catch (IOException ignored) {}
        }
    }
}