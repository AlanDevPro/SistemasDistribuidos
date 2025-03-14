/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab1_pagosruat;

/**
 *
 * @author TODO LAPTOP
 */
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class Cliente{
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            IBanco banco = (IBanco) registry.lookup("Banco");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese su CI: ");
            String CI = scanner.nextLine();

            List<Deuda> deudas = banco.ObtenerDeuda(CI);
            if (deudas.isEmpty()) {
                System.out.println("No tiene deudas.");
                return;
            }

            System.out.println("Deudas encontradas:");
            for (int i = 0; i < deudas.size(); i++) {
                System.out.println(i + ". " + deudas.get(i).getImpuesto() + " - " + deudas.get(i).getMonto());
            }

            System.out.print("Seleccione una deuda para pagar (ingrese el número): ");
            int index = scanner.nextInt();

            if (index >= 0 && index < deudas.size()) {
                boolean pagoExitoso = banco.Pagar(deudas.get(index));
                if (pagoExitoso) {
                    System.out.println("Pago realizado con éxito.");
                } else {
                    System.out.println("No se pudo realizar el pago. Verifique si su CI tiene observaciones.");
                }
            } else {
                System.out.println("Selección inválida.");
            }

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

