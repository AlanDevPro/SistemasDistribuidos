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
import java.util.ArrayList;
import java.util.List;

public class Ruat extends UnicastRemoteObject implements IBanco {
    private List<Deuda> deudas;
    private IAlcaldia alcaldia;

    public Ruat(Alcaldia alcaldia) throws RemoteException {
        super();
        this.alcaldia = alcaldia;
        deudas = new ArrayList<>();
        deudas.add(new Deuda("1234567", 2022, "Vehículo", 2451));
        deudas.add(new Deuda("1234567", 2022, "Casa", 2500));
        deudas.add(new Deuda("555587", 2021, "Vehículo", 5000));
        deudas.add(new Deuda("333357", 2023, "Casa", 24547));
    }

    public List<Deuda> BuscarDeudas(String CI) throws RemoteException {
        List<Deuda> resultado = new ArrayList<>();
        for (Deuda d : deudas) {
            if (d.getCI().equals(CI)) {
                resultado.add(d);
            }
        }
        return resultado;
    }

    @Override
    public boolean Pagar(Deuda deuda) throws RemoteException {
        if (alcaldia.BuscarObservaciones(deuda.getCI())) {
            return false;
        }
        return deudas.remove(deuda);
    }

    @Override
    public List<Deuda> ObtenerDeuda(String CI) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
