package socketsudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "localhost";
        final int SERVER_PORT = 6789;

        try (DatagramSocket socketUDP = new DatagramSocket();
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Cliente UDP iniciado...");

            while (true) {
                System.out.println("\nSeleccione una opción:");
                System.out.println("1. Establecer número (setN)");
                System.out.println("2. Calcular Fibonacci");
                System.out.println("3. Calcular Factorial");
                System.out.println("4. Calcular Sumatoria");
                System.out.println("5. Salir");
                System.out.print("Opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                String mensaje = "";

                if (opcion == 1) {
                    System.out.print("Ingrese un número: ");
                    int numero = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    mensaje = "setN-" + numero;
                } else if (opcion == 2) {
                    mensaje = "fibonacci";
                } else if (opcion == 3) {
                    mensaje = "factorial";
                } else if (opcion == 4) {
                    mensaje = "sumatoria";
                } else if (opcion == 5) {
                    System.out.println("Saliendo...");
                    break;
                } else {
                    System.out.println("Opción inválida. Intente nuevamente.");
                    continue;
                }

                byte[] buffer = mensaje.getBytes();
                InetAddress serverAddress = InetAddress.getByName(SERVER_ADDRESS);
                DatagramPacket request = new DatagramPacket(buffer, buffer.length, serverAddress, SERVER_PORT);
                socketUDP.send(request);

                // Recibir la respuesta del servidor
                byte[] responseBuffer = new byte[1000];
                DatagramPacket response = new DatagramPacket(responseBuffer, responseBuffer.length);
                socketUDP.receive(response);

                String respuesta = new String(response.getData(), 0, response.getLength());
                System.out.println("Respuesta del servidor: " + respuesta);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
