package socketsudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {
    public static void main(String[] args) {
        int port = 6789;
        byte[] buffer = new byte[1000];

        try {
            DatagramSocket socketUDP = new DatagramSocket(port);
            System.out.println("Servidor UDP iniciado en el puerto " + port);

            Operaciones o = new Operaciones();

            while (true) {
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                socketUDP.receive(peticion);

                String cadena = new String(peticion.getData(), 0, peticion.getLength());
                String[] opciones = cadena.split("-");

                String respuestaStr = "";

                switch (opciones[0].trim().toLowerCase()) {     
                    case "setn":
                        int valor = Integer.parseInt(opciones[1].trim());
                        o.setN(valor);
                        respuestaStr = "Número establecido en " + valor;
                        break;
                    case "fibonacci":
                        respuestaStr = "Fibonacci(" + o.getN() + ") = " + o.fibonacci();
                        break;
                    case "factorial":
                        respuestaStr = "Factorial(" + o.getN() + ") = " + o.factorial();
                        break;
                    case "sumatoria":
                        respuestaStr = "Sumatoria(" + o.getN() + ") = " + o.sumatoria();
                        break;
                    default:
                        respuestaStr = "Opción inválida";
                }

                byte[] mensaje = respuestaStr.getBytes();
                DatagramPacket respuesta = new DatagramPacket(mensaje, mensaje.length, peticion.getAddress(), peticion.getPort());
                socketUDP.send(respuesta);
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }
}
