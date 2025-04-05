package ejemploprimerparcial.pagosruat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.util.List;

public class BancoServerTCP {
    private static final int PUERTO = 5002;

    public static void main(String[] args) {
        try {
            // Conectar al servidor RUAT vía RMI
            IRuat ruat = (IRuat) Naming.lookup("rmi://localhost:1101/RUAT");
            System.out.println("Conectado al servidor RUAT vía RMI.");

            try (ServerSocket servidor = new ServerSocket(PUERTO)) {
                System.out.println("Servidor Banco en espera de conexiones...");

                while (true) {
                    try (Socket socket = servidor.accept();
                         BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                         PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {

                        System.out.println("Cliente conectado.");
                        String ci = entrada.readLine();
                        System.out.println("CI recibido: " + ci);

                        // Obtener deudas del RUAT
                        List<Deuda> deudas = ruat.buscar(ci);
                        

                        if (deudas == null || deudas.isEmpty()) {
                            salida.println("No se encontraron deudas para el usuario con CI " + ci);
                        } else {
                            for (Deuda deuda : deudas) {
                                salida.println(deuda.getAnio() + ", " + deuda.getImpuesto() + ", " + deuda.getMonto());
                            }
                        }

                        // Esperar si el cliente quiere pagar
                        String comando = entrada.readLine();
                        if ("PAGAR".equals(comando)) {
                            String detallePago = entrada.readLine();
                            System.out.println("Solicitud de pago: " + detallePago);

                            String[] partes = detallePago.split(", ");
                            String anio = partes[0];
                            String impuesto = partes[1];
                            double monto = Double.parseDouble(partes[2]);

                            Deuda deudaPagar = new Deuda(ci, Integer.parseInt(anio), impuesto, monto);
                            boolean pagoExitoso = ruat.pagar(deudaPagar);

                            if (pagoExitoso) {
                                salida.println("PAGO EXITOSO");
                            } else {
                                salida.println("PAGO FALLIDO");
                            }
                        }

                        System.out.println("Respuesta enviada.");
                    } catch (IOException e) {
                        System.out.println("Error con un cliente: " + e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}

