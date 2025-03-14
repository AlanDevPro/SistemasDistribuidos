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
import java.util.List;

public class Banco extends UnicastRemoteObject implements IBanco {
    private IBanco ruat;

    public Banco(IBanco ruat) throws RemoteException {
        super();
        this.ruat = ruat;
    }

    public List<Deuda> ObtenerDeuda(String CI) throws RemoteException {
        return ruat.ObtenerDeuda(CI);
    }

    public boolean Pagar(Deuda deuda) throws RemoteException {
        return ruat.Pagar(deuda);
    }
}

