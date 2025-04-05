package ejemploprimerparcial.pagosruat;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Map;

public class ServidorAlcaldiaUDP {

    private static final Map<String, Boolean> observaciones = new HashMap<>();

    public static void main(String[] args) {
        try {
            try (DatagramSocket socket = new DatagramSocket(5000)) {
                System.out.println("Servidor de Alcaldía (UDP) en espera de consultas...");

                while (true) {
                    byte[] buffer = new byte[1024];
                    DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                    socket.receive(request);

                    String consulta = new String(request.getData(), 0, request.getLength());
                    System.out.println("Consulta recibida: " + consulta);

                    // Verificar si la deuda está observada
                    boolean resultado = observaciones.getOrDefault(consulta, false);

                    // Enviar respuesta al cliente (ServidorRuat)
                    String respuesta = resultado ? "true" : "false";
                    byte[] responseData = respuesta.getBytes();
                    DatagramPacket response = new DatagramPacket(responseData, responseData.length,
                            request.getAddress(), request.getPort());
                    socket.send(response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
