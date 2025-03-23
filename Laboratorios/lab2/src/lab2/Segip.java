/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
/**
 *
 * @author luisc
 */
public class Segip extends UnicastRemoteObject implements ISegip  {

    public Segip() throws RemoteException {
        super();
    }

    @Override
    public boolean Verificar(String ci, String nombres, String apellidos) throws RemoteException {
        // Simulation of verification process
        // In a real implementation, this would check against a database
        System.out.println("SEGIP: Verificando CI: " + ci + ", Nombres: " + nombres + ", Apellidos: " + apellidos);
        
        // For this example, we'll consider valid if:
        // - CI has 7 digits
        // - Names and surnames have at least 3 characters
        boolean isValid = ci.length() == 7 && 
                          nombres.length() >= 3 && 
                          apellidos.length() >= 3;
        
        System.out.println("SEGIP: Resultado de verificacion: " + (isValid ? "Válido" : "Inválido"));
        return isValid;
    }
    
}