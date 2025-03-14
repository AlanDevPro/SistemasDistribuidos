/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lab1_pagosruat;

import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author TODO LAPTOP
 */
public interface IRuat {
    List<Deuda> BuscarDeudas(String CI) throws RemoteException;
    boolean Pagar(Deuda deuda) throws RemoteException;
}
