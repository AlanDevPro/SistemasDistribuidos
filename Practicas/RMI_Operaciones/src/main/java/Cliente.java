/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author TODO LAPTOP
 */
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Operaciones obj = (Operaciones) registry.lookup("Operaciones");
            
            Scanner scanner = new Scanner(System.in);
            int opcion, n;
            
            while (true) {
                System.out.println("\nMenú de Operaciones:");
                System.out.println("1. Definir valor de n");
                System.out.println("2. Calcular Factorial");
                System.out.println("3. Calcular Serie Fibonacci");
                System.out.println("4. Calcular Sumatoria");
                System.out.println("5. Salir");
                System.out.print("Elija una opción: ");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese el valor de n: ");
                        n = scanner.nextInt();
                        obj.setN(n);
                        System.out.println("Valor de n definido como " + n);
                        break;
                    case 2:
                        System.out.println("Factorial: " + obj.Factorial());
                        break;
                    case 3:
                        int[] fibonacci = obj.Fibonacci();
                        System.out.print("Serie Fibonacci: ");
                        for (int num : fibonacci) {
                            System.out.print(num + " ");
                        }
                        System.out.println();
                        break;
                    case 4:
                        System.out.println("Sumatoria: " + obj.Sumatoria());
                        break;
                    case 5:
                        System.out.println("Saliendo...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Opción inválida, intente de nuevo.");
                }
            }
        } catch (Exception e) {
            System.err.println("Error en el cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


