package ejemploprimerparcial.pagosruat;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class Ruat {
    public static void main(String[] args) {
        try {
            // Conexión con el registro RMI en el servidor
            Registry registry = LocateRegistry.getRegistry("localhost", 1101);  // Asegúrate de que el puerto 1101 sea el correcto

            // Buscar el objeto remoto en el registro RMI
            IRuat stub = (IRuat) registry.lookup("RUAT");  // "Ruat" debe coincidir con el nombre registrado en el servidor

            System.out.println("Conexión exitosa con el servidor RMI.");

            // Ejemplo de uso del método buscar
            String ci = "12345678"; // CI del usuario a consultar
            ArrayList<Deuda> deudas = stub.buscar(ci);
            
            System.out.println("Deudas para CI: " + ci);
            for (Deuda deuda : deudas) {
                System.out.println(deuda);
            }

            // Ejemplo de uso del método pagar
            Deuda deudaParaPagar = new Deuda("12345678", 2021, "Impuesto Vehicular", 500);
            boolean pagoExitoso = stub.pagar(deudaParaPagar);

            if (pagoExitoso) {
                System.out.println("Pago procesado con éxito.");
            } else {
                System.out.println("No se pudo procesar el pago.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
