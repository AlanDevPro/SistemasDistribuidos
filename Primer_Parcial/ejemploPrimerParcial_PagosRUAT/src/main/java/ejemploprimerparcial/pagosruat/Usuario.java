package ejemploprimerparcial.pagosruat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Usuario {
    public static void main(String[] args) {
        final String SERVIDOR = "localhost"; // Dirección del servidor
        final int PUERTO = 5002; // Puerto del servidor

        try (Socket socket = new Socket(SERVIDOR, PUERTO);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Scanner scanner = new Scanner(System.in)) {

            // Menú principal
            boolean continuar = true;
            while (continuar) {
                System.out.println("1. Consultar deudas");
                System.out.println("2. Pagar deuda");
                System.out.println("3. Salir Programa");
                System.out.print("Opción: ");

                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer

                switch (opcion) {
                    case 1:
                        // Opción para consultar deudas
                        System.out.print("Ingrese su CI: ");
                        String ciConsulta = scanner.nextLine();
                        out.writeUTF("deuda"); // Comando para consultar deudas
                        out.writeUTF(ciConsulta); // Enviar el CI al servidor
                        out.flush();

                        // Leer las deudas del servidor
                        List<Deuda> deudas = (List<Deuda>) in.readObject();

                        if (deudas.isEmpty()) {
                            System.out.println("No se encontraron deudas para el CI: " + ciConsulta);
                        } else {
                            System.out.println("Deudas encontradas:");
                            deudas.forEach(System.out::println);

                            // Preguntar si desea pagar alguna deuda
                            System.out.println("¿Desea pagar alguna deuda? (SI/NO)");
                            String respuestaPago = scanner.nextLine();

                            if (respuestaPago.equalsIgnoreCase("SI")) {
                                // Solicitar detalles de pago
                                System.out.print("Ingrese el año de la deuda a pagar: ");
                                int anio = scanner.nextInt();
                                scanner.nextLine(); // Limpiar el buffer
                                System.out.print("Ingrese el tipo de impuesto: ");
                                String impuesto = scanner.nextLine();
                                System.out.print("Ingrese el monto: ");
                                double monto = scanner.nextDouble();
                                scanner.nextLine(); // Limpiar el buffer

                                // Enviar solicitud de pago
                                out.writeUTF("pagar"); // Comando para pagar deuda
                                out.writeObject(new Deuda(ciConsulta, anio, impuesto, monto)); // Enviar la deuda a
                                                                                               // pagar
                                out.flush();

                                // Recibir respuesta de pago
                                boolean pagado = in.readBoolean();
                                if (pagado) {
                                    System.out.println("Pago exitoso.");
                                } else {
                                    System.out.println("No se puede pagar la deuda (observada) o error.");
                                }
                            }
                        }
                        break;

                    case 2:
                        // Opción para pagar deuda directamente (puede ser parte de la opción 1, como lo
                        // mencioné)
                        System.out.print("Ingrese su CI: ");
                        String ciPago = scanner.nextLine();
                        System.out.print("Ingrese el año de la deuda a pagar: ");
                        int anioPago = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer
                        System.out.print("Ingrese el tipo de impuesto: ");
                        String impuestoPago = scanner.nextLine();
                        System.out.print("Ingrese el monto: ");
                        double montoPago = scanner.nextDouble();
                        scanner.nextLine(); // Limpiar el buffer

                        // Enviar solicitud de pago
                        out.writeUTF("pagar"); // Comando para pagar deuda
                        out.writeObject(new Deuda(ciPago, anioPago, impuestoPago, montoPago)); // Enviar la deuda a
                                                                                               // pagar
                        out.flush();

                        // Recibir respuesta de pago
                        boolean pagoExitoso = in.readBoolean();
                        if (pagoExitoso) {
                            System.out.println("Pago exitoso.");
                        } else {
                            System.out.println("No se puede pagar la deuda (observada) o error.");
                        }
                        break;

                    case 3:
                        // Salir del programa
                        continuar = false;
                        System.out.println("Saliendo del programa...");
                        break;

                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {

        }
    }
}
