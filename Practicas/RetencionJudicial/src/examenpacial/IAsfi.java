/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package examenpacial;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
/**
 *
 * @author Dell
 */
public interface IAsfi extends Remote {
    public ArrayList<Cuenta> consultarCuentas(String ci, String nombres, String apellidos) throws RemoteException;
    public boolean retenerMonto  (Cuenta cuenta,double montoBs, String glosa) throws RemoteException;
    
}
