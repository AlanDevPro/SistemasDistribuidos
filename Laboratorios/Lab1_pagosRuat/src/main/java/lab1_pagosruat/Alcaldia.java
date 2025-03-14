/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab1_pagosruat;

/**
 *
 * @author TODO LAPTOP
 */
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class Alcaldia extends UnicastRemoteObject implements IAlcaldia {
    private Map<String, Boolean> observaciones;

    public Alcaldia() throws RemoteException {
        super();
        observaciones = new HashMap<>();
        observaciones.put("1234567", true); // Este CI tiene observaciones
    }

    public boolean BuscarObservaciones(String CI) {
        return observaciones.getOrDefault(CI, false);
    }

    @Override
    public boolean ObtenerDeuda(String CI) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
