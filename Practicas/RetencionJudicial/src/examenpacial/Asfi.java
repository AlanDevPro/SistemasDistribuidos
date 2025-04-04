/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examenpacial;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Dell
 */
public class Asfi extends UnicastRemoteObject implements IAsfi {

    public Asfi() throws RemoteException {
        super();
    }
    

    @Override
    public ArrayList<Cuenta> consultarCuentas(String ci, String nombres, String apellidos) throws RemoteException {
        ArrayList<Cuenta> cuentas = new ArrayList<>();

    // Consultar Banco Mercantil (UDP)
    String respuestaMercantil = Mercantil.buscarCuenta(ci, nombres, apellidos);
    if (respuestaMercantil != null && !respuestaMercantil.isEmpty()) {
        for (String datos : respuestaMercantil.split(":")) {
            String[] partes = datos.split("-");
            if (partes.length == 2) {  // Verificar que los datos están bien formados
                cuentas.add(new Cuenta(Banco.Mercantil, partes[0], ci, nombres, apellidos, Double.parseDouble(partes[1])));
            }
        }
    }

    // Consultar Banco BCP (TCP)
    String respuestaBCP = BCP.buscarCuenta(ci, nombres, apellidos);
    if (respuestaBCP != null && !respuestaBCP.isEmpty()) {
        for (String datos : respuestaBCP.split(":")) {
            String[] partes = datos.split("-");
            if (partes.length == 2) {  // Verificar que los datos están bien formados
                cuentas.add(new Cuenta(Banco.BCB, partes[0], ci, nombres, apellidos, Double.parseDouble(partes[1])));
            }
        }
    }

    System.out.println("📌 Cuentas : " + cuentas);
    return cuentas;
    }


    @Override
    public boolean retenerMonto(Cuenta cuenta, double montoBs, String glosa) {
    String mensaje = "Congelar:" + cuenta.getNroCuenta() + "-" + montoBs;
    String respuesta = "";

    if (cuenta.getBanco() == Banco.Mercantil) {
        respuesta = Mercantil.retenerMonto(mensaje);
    } else if (cuenta.getBanco() == Banco.BCB) {
        respuesta = BCP.retenerMonto(mensaje);
    }

    return respuesta != null && respuesta.startsWith("SI");
    }

    
}
