/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examenpacial;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.rmi.registry.LocateRegistry;
import java.rmi.Naming;

/**
 *
 * @author Dell
 */
public class ServerAsfi {
    
    public static void main (String[] args)
    {
        try {
            Asfi asfi=new Asfi();
            LocateRegistry.createRegistry(1099);
            Naming.bind("Asfi", asfi);
            System.out.println("Servidor ASFI iniciado correctamente.");
            
        } catch (RemoteException | AlreadyBoundException | MalformedURLException ex) {
            Logger.getLogger(ServerAsfi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
