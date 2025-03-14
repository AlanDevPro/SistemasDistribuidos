/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lab1_pagosruat;

/**
 *
 * @author TODO LAPTOP
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IBanco extends Remote {
    List<Deuda> ObtenerDeuda(String CI) throws RemoteException;
    boolean Pagar(Deuda deuda) throws RemoteException;
}
