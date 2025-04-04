package examenpacial;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

public class Juez {
    public static void main(String[] args) {
        try {
            // Conectarse al servidor ASFI usando RMI
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            IAsfi servidorAsfi = (IAsfi) registry.lookup("Asfi");

            Scanner scanner = new Scanner(System.in);
            ArrayList<Cuenta> cuentas = new ArrayList<>();
            
            while (true) {
                System.out.println("\n--- MENÚ JUEZ ---");
                System.out.println("1. Buscar cuentas");
                System.out.println("2. Salir");
                System.out.print("Seleccione una opción: ");
                
                int opcion;
                try {
                    opcion = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("⚠️ Ingrese un número válido.");
                    continue;
                }

                switch (opcion) {
                    case 1:  // Corregido (Evitar "->" aquí si se usa más de una línea de código)
                        // Solicitar datos de búsqueda
                        System.out.print("Ingrese CI: ");
                        String ci = scanner.nextLine();
                        System.out.print("Ingrese nombres: ");
                        String nombres = scanner.nextLine();
                        System.out.print("Ingrese apellidos: ");
                        String apellidos = scanner.nextLine();
                        
                        // Consultar cuentas en ASFI
                        cuentas = servidorAsfi.consultarCuentas(ci, nombres, apellidos);

                        if (cuentas.isEmpty()) {
                            System.out.println("\n⚠️  No se encontraron cuentas para el titular: " + nombres + " " + apellidos);
                        } else {
                            System.out.println("\n=====================================");
                            System.out.println("         📌 Cuentas Encontradas       ");
                            System.out.println("=====================================");

                            for (int i = 0; i < cuentas.size(); i++) {
                                Cuenta cuenta = cuentas.get(i);
                                System.out.printf("🔹 Cuenta %d\n", i + 1);
                                System.out.printf("   🏦 Banco: %s\n", cuenta.getBanco());
                                System.out.printf("   🆔 NroCuenta: %s\n", cuenta.getNroCuenta());
                                System.out.printf("   💰 Saldo: Bs. %.2f\n", cuenta.getSaldo());
                                System.out.println("=====================================");
                            }

                            // Selección de cuenta
                            System.out.print("Seleccione el número de cuenta: ");
                            int seleccion;
                            try {
                                seleccion = Integer.parseInt(scanner.nextLine());
                                if (seleccion < 1 || seleccion > cuentas.size()) {
                                    System.out.println("⚠️ Selección inválida.");
                                    break;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("⚠️ Ingrese un número válido.");
                                break;
                            }

                            System.out.print("Ingrese monto a retener: ");
                            double monto;
                            try {
                                monto = Double.parseDouble(scanner.nextLine());
                                if (monto <= 0) {
                                    System.out.println("⚠️ El monto debe ser mayor a 0.");
                                    break;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("⚠️ Ingrese un monto válido.");
                                break;
                            }

                            Cuenta cuentaSeleccionada = cuentas.get(seleccion - 1);
                            boolean exito = servidorAsfi.retenerMonto(cuentaSeleccionada, monto, "Retención judicial");
                            
                            if (exito) {
                                System.out.println("✅ Transacción con éxito.");
                            } else {
                                System.out.println("❌ No se pudo realizar la retención.");
                            }
                        }
                        break;  // Se agregó el break para evitar problemas

                    case 2:
                        System.out.println("Saliendo del sistema...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("⚠️ Opción no válida.");
                }
            }
        } catch (NotBoundException | RemoteException e) {
            System.err.println("⚠️ Error en la conexión con ASFI: " + e.getMessage());
        }
    }
}
