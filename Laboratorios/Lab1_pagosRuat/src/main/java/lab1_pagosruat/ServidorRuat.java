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

public class ServidorRuat {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1101);
            IAlcaldia alcaldia = (IAlcaldia) registry.lookup("Alcaldia");

            Ruat ruat = new Ruat((Alcaldia) alcaldia);
            registry.rebind("Ruat", ruat);

            System.out.println("Servidor RUAT listo...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

