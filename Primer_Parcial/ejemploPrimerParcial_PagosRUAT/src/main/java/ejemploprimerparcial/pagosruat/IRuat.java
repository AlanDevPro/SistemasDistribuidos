package ejemploprimerparcial.pagosruat;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IRuat extends Remote {
    ArrayList<Deuda> buscar(String ci) throws RemoteException;
    boolean pagar(Deuda deuda) throws RemoteException;
}
