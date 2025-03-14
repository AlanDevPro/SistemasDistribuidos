/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lab1_pagosruat;

import java.rmi.RemoteException;

/**
 *
 * @author TODO LAPTOP
 */
public interface IAlcaldia {
    boolean ObtenerDeuda(String CI) throws RemoteException;

    public boolean BuscarObservaciones(String ci);
}
