/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab1_pagosruat;

/**
 *
 * @author TODO LAPTOP
 */
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServidorBanco {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1100);
            IBanco ruat = (IBanco) registry.lookup("Ruat");

            Banco banco = new Banco(ruat);
            registry.rebind("Banco", banco);

            System.out.println("Servidor Banco listo...");
        } catch (NotBoundException | RemoteException e) {
        }
    }
}

