import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerBancoBCP {
    public static void main(String[] args) {
        int puerto = 6000;  // Puerto TCP
        Map<String, String> cuentas = new HashMap<>();
        
        // Base de datos simulada (CI -> "NúmeroCuenta-Saldo")
        cuentas.put("12345678", "555666777-3500.00");
        cuentas.put("87654321", "111222333-2200.25");

        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("🏦 Banco BCP escuchando en el puerto " + puerto + " (TCP)...");

            while (true) {
                Socket socket = serverSocket.accept();  // Espera conexiones
                System.out.println("✅ Cliente conectado: " + socket.getInetAddress());

                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter salida = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

                String mensaje = entrada.readLine();
                System.out.println("📩 Consulta recibida: " + mensaje);

                // Verificar que el formato sea "Buscar:ci-nombres-apellidos"
                String[] partes = mensaje.split(":");
                if (partes.length != 2 || !partes[0].equals("Buscar")) {
                    salida.println("");  // Respuesta vacía si el mensaje no es válido
                    socket.close();
                    continue;
                }

                // Obtener los valores CI, nombres y apellidos
                String[] datos = partes[1].split("-");
                if (datos.length != 3) {
                    salida.println("");  // Respuesta vacía si los datos no están bien formateados
                    socket.close();
                    continue;
                }

                String ci = datos[0];
                String nombres = datos[1];
                String apellidos = datos[2];

                // Generar la respuesta con el formato adecuado
                StringBuilder respuesta = new StringBuilder();

                // Buscar la cuenta para el CI
                String cuenta = cuentas.getOrDefault(ci, "");
                if (!cuenta.isEmpty()) {
                    respuesta.append(cuenta);  // Agregar cuenta y saldo
                } else {
                    respuesta.append("Cuenta no encontrada"); // Respuesta cuando no se encuentra la cuenta
                }

                salida.println(respuesta);  // Enviar la respuesta al cliente
                System.out.println("📤 Respuesta enviada: " + respuesta);

                socket.close();
            }
        } catch (Exception e) {
            System.err.println("❌ Error en Banco BCP: " + e.getMessage());
        }
    }
}
