package examenpacial;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class ServerBancoMercantil {
    public static void main(String[] args) {
        int puerto = 5000;  // Puerto UDP
        Map<String, String> cuentas = new HashMap<>();
        
        // Base de datos simulada (CI -> "NúmeroCuenta-Saldo")
        cuentas.put("12345678", "987654321-2500.75");
        cuentas.put("87654321", "123456789-1500.50");

        try (DatagramSocket socket = new DatagramSocket(puerto)) {
            System.out.println("🏦 Banco Mercantil escuchando en el puerto " + puerto + " (UDP)...");

            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket paqueteEntrada = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteEntrada);

                String mensaje = new String(paqueteEntrada.getData(), 0, paqueteEntrada.getLength());
                System.out.println("📩 Consulta recibida: " + mensaje);

                // Verificar si el mensaje es de tipo "Buscar"
                String[] partes = mensaje.split(":");
                if (partes.length < 2) {
                    continue;  // Mensaje inválido
                }

                String comando = partes[0];
                if (comando.equals("Buscar")) {
                    // Extraer los datos del mensaje
                    String[] datos = partes[1].split("-");
                    if (datos.length < 3) {
                        continue;  // No hay suficientes datos en el mensaje
                    }

                    String ci = datos[0];  // CI
                    String nombres = datos[1];  // Nombres
                    String apellidos = datos[2];  // Apellidos

                    // Generar la respuesta con el formato adecuado
                    StringBuilder respuesta = new StringBuilder();

                    // Buscar la cuenta para el CI
                    String cuenta = cuentas.getOrDefault(ci, "");
                    if (!cuenta.isEmpty()) {
                        respuesta.append(cuenta);  // Agregar cuenta y saldo
                    } else {
                        respuesta.append(""); // Respuesta cuando no se encuentra la cuenta
                    }

                    // Enviar la respuesta
                    byte[] datosRespuesta = respuesta.toString().getBytes();
                    InetAddress direccionCliente = paqueteEntrada.getAddress();
                    int puertoCliente = paqueteEntrada.getPort();

                    DatagramPacket paqueteSalida = new DatagramPacket(datosRespuesta, datosRespuesta.length, direccionCliente, puertoCliente);
                    socket.send(paqueteSalida);
                    System.out.println("📤 Respuesta enviada: " + respuesta.toString());
                } else if (comando.equals("Congelar")) {
                    // Caso de congelar la cuenta
                    String[] datosCongelar = partes[1].split("-");
                    if (datosCongelar.length < 2) {
                        continue;  // Datos incorrectos para congelar
                    }

                    String cuenta = datosCongelar[0];  // NroCuenta
                    String monto = datosCongelar[1];  // Monto a congelar
                    
                    System.out.println("📤 Respuesta congelación cuenta: " + cuenta);  // Imprime el número de cuenta
System.out.println("📤 Respuesta congelación monto: " + monto);

                    // Verificar si la cuenta existe
                    StringBuilder respuestaCongelar = new StringBuilder();
                   String cuentaInfo = cuentas.get(cuenta);  // Intenta obtener la cuenta
                   if (cuentaInfo != null) {
                        respuestaCongelar.setLength(0);  // Limpiar el StringBuilder
                        respuestaCongelar.append("SI-").append(cuenta);  // Si la cuenta existe, devolver "SI" con el número de cuenta
                   } else {
                        respuestaCongelar.setLength(0);  // Limpiar el StringBuilder
                        respuestaCongelar.append("No-no encontrado");  // Si la cuenta no se encuentra, devolver "No-no encontrado"
                  }

                    // Enviar la respuesta de congelación
                    byte[] datosRespuestaCongelar = respuestaCongelar.toString().getBytes();
                    InetAddress direccionCliente = paqueteEntrada.getAddress();
                    int puertoCliente = paqueteEntrada.getPort();

                    DatagramPacket paqueteSalidaCongelar = new DatagramPacket(datosRespuestaCongelar, datosRespuestaCongelar.length, direccionCliente, puertoCliente);
                    socket.send(paqueteSalidaCongelar);
                    System.out.println("📤 Respuesta congelación enviada: " + respuestaCongelar.toString());
                }
            }
        } catch (Exception e) {
            System.err.println("❌ Error en Banco Mercantil: " + e.getMessage());
            e.printStackTrace();  // Imprimir detalles de la excepción
        }
    }
}
