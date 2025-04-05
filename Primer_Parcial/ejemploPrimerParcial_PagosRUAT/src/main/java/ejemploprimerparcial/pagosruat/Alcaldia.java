package ejemploprimerparcial.pagosruat;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Alcaldia {
    private static final String ALCALDIA_HOST = "localhost"; // Cambia si el servidor está en otro host
    private static final int ALCALDIA_PORT = 5000;

    public static boolean estaObservada(int anio, String impuesto) {
        try (DatagramSocket socket = new DatagramSocket()) {
            String mensaje = anio + ";" + impuesto;
            byte[] buffer = mensaje.getBytes();

            InetAddress address = InetAddress.getByName(ALCALDIA_HOST);
            DatagramPacket requestPacket = new DatagramPacket(buffer, buffer.length, address, ALCALDIA_PORT);
            socket.send(requestPacket); // Enviar solicitud

            // Recibir respuesta
            byte[] responseBuffer = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
            socket.receive(responsePacket);

            String respuesta = new String(responsePacket.getData(), 0, responsePacket.getLength());
            return respuesta.equalsIgnoreCase("true");
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Si hay error, asumir que no está observada
        }
    }
}
