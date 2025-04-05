package ejemploprimerparcial.pagosruat;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class ServidorRuat extends UnicastRemoteObject implements IRuat {
    private Map<String, List<Deuda>> deudas;

    public ServidorRuat() throws RemoteException {
        deudas = new HashMap<>(); // inicializar el mapa

        agregarDeuda("1234567", new Deuda("1234567", 2021, "Vehiculo", 500));
        agregarDeuda("1234567", new Deuda("1234567", 2022, "Casa", 1200));
        agregarDeuda("555587", new Deuda("555587", 2023, "Vehiculo", 300));
        agregarDeuda("333357", new Deuda("333357", 2022, "Casa", 1200));
    }

    // Método auxiliar para agregar deudas al mapa
    private void agregarDeuda(String ci, Deuda deuda) {
        deudas.computeIfAbsent(ci, _ -> new ArrayList<>()).add(deuda);
    }

    @Override
    public ArrayList<Deuda> buscar(String ci) {
        if (deudas.containsKey(ci)) {
            return new ArrayList<>(deudas.get(ci));
        } else {
            return new ArrayList<>(); // Lista vacía si no hay deudas
        }
    }

    @Override
    public boolean pagar(Deuda deuda) {
        try {
            // Consultar a la Alcaldía usando UDP
            boolean tieneObservacion = consultarAlcaldiaUDP(deuda.getAnio(), deuda.getImpuesto());

            if (tieneObservacion) {
                System.out.println("La deuda tiene observaciones según la alcaldía.");
                return true; // Tiene observaciones
            } else {
                System.out.println("La deuda NO tiene observaciones.");
                return false; // No tiene observaciones
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false; // En caso de error se asume no pagable
        }
    }

    private boolean consultarAlcaldiaUDP(int anio, String impuesto) {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName("localhost"); // IP del servidor UDP
            int port = 5000; // Puerto donde escucha la alcaldía

            // Construir el mensaje a enviar: "2023-Vehiculo"
            String consulta = anio + "-" + impuesto;
            byte[] buffer = consulta.getBytes();

            // Enviar el paquete UDP
            DatagramPacket request = new DatagramPacket(buffer, buffer.length, address, port);
            socket.send(request);

            // Esperar respuesta
            byte[] responseBuffer = new byte[1024];
            DatagramPacket response = new DatagramPacket(responseBuffer, responseBuffer.length);
            socket.receive(response);

            String respuesta = new String(response.getData(), 0, response.getLength());
            return respuesta.trim().equalsIgnoreCase("true");

        } catch (Exception e) {
            e.printStackTrace();
            return false; // En caso de error, asumir que no tiene observación
        }
    }

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1101); // Crear registro RMI en el puerto 1101
            ServidorRuat ruat = new ServidorRuat();
            Naming.rebind("rmi://localhost:1101/RUAT", ruat); // Registrar objeto
            System.out.println("Servidor RUAT RMI iniciado en puerto 1101.");
        } catch (Exception e) {
            System.out.println("Error al iniciar el servidor RUAT: " + e.getMessage());
            e.printStackTrace();
        }

    }

}
