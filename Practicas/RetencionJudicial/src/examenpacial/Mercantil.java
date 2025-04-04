package examenpacial;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Mercantil {
    private static final String HOST = "localhost";
    private static final int PUERTO = 5000; // Puerto del banco Mercantil

    public static String buscarCuenta(String ci, String nombres, String apellidos) {
        String mensaje = "Buscar:" + ci + "-" + nombres + "-" + apellidos;
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress direccion = InetAddress.getByName(HOST);
            byte[] bufferEnvio = mensaje.getBytes();
            DatagramPacket paqueteEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length, direccion, PUERTO);
            socket.send(paqueteEnvio);

            byte[] bufferRecepcion = new byte[1024];
            DatagramPacket paqueteRecepcion = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);
            socket.receive(paqueteRecepcion);

            return new String(paqueteRecepcion.getData(), 0, paqueteRecepcion.getLength()).trim();
            
        } catch (Exception e) {
            e.printStackTrace();  // Imprimir la excepción para mayor claridad
            return "";
        }
    }
    

    public static String retenerMonto(String mensaje) {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress direccion = InetAddress.getByName("localhost");
            byte[] bufferEnvio = mensaje.getBytes();
            DatagramPacket paqueteEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length, direccion, PUERTO);
            socket.send(paqueteEnvio);

            byte[] bufferRecepcion = new byte[1024];
            DatagramPacket paqueteRecepcion = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);
            socket.receive(paqueteRecepcion);

            return new String(paqueteRecepcion.getData(), 0, paqueteRecepcion.getLength()).trim();
        } catch (Exception e) {
            e.printStackTrace();  // Imprimir la excepción para mayor claridad
            return "No-no encontrado";
        }
    }
}
