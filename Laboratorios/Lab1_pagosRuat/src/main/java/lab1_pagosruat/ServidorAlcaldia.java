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

public class ServidorAlcaldia {
    public static void main(String[] args) {
        try {
            Alcaldia obj = new Alcaldia();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Alcaldia", obj);
            System.out.println("Servidor Alcaldía listo...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

