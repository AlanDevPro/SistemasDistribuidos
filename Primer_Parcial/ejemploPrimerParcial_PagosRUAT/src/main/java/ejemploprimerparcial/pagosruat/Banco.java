package ejemploprimerparcial.pagosruat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Banco {
    private final String servidor = "localhost";
    private final int puerto = 5002;

    // Método para consultar deudas del usuario
    public List<String> deuda(String ci) {
        List<String> deudas = new ArrayList<>();
        
        try (Socket socket = new Socket(servidor, puerto);
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            salida.println(ci); // Enviar CI al servidor
            
            String respuesta;
            while ((respuesta = entrada.readLine()) != null) {
                if (respuesta.startsWith("Deudas del Banco")) {
                    continue; // Saltar la línea de cabecera
                }
                if (respuesta.startsWith("No se encontraron deudas")) {
                    return deudas; // Retorna lista vacía si no hay deudas
                }
                deudas.add(respuesta); // Agregar cada deuda a la lista
            }
        } catch (IOException e) {
            System.out.println("Error en la conexión con el banco: " + e.getMessage());
        }
        
        return deudas;
    }

    // Método para realizar pagos
    public boolean pagar(String ci, String anio, String impuesto, double monto) {
        try (Socket socket = new Socket(servidor, puerto);
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            salida.println(ci); // Enviar CI al servidor
            
            String respuesta;
            while ((respuesta = entrada.readLine()) != null) {
                if (respuesta.startsWith("No se encontraron deudas")) {
                    return false;
                }
            }

            // Enviar comando de pago
            salida.println("PAGAR");
            salida.println(anio + ", " + impuesto + ", " + monto);
            
            String confirmacion = entrada.readLine();
            return "PAGO EXITOSO".equals(confirmacion);
        } catch (IOException e) {
            System.out.println("Error en la conexión con el banco: " + e.getMessage());
            return false;
        }
    }
    
}
