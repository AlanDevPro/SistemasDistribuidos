import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Operaciones extends Remote {
    void setN(int n) throws RemoteException;
    long Factorial() throws RemoteException;
    int[] Fibonacci() throws RemoteException;
    int Sumatoria() throws RemoteException;
}


