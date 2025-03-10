

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class OperacionesImpl extends UnicastRemoteObject implements Operaciones {
    private int n;

    public OperacionesImpl() throws RemoteException {
        super();
        this.n = 0;
    }

    @Override
    public void setN(int n) throws RemoteException {
        this.n = n;
    }

    @Override
    public long Factorial() throws RemoteException {
        long fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }

    @Override
    public int[] Fibonacci() throws RemoteException {
        int[] fib = new int[n];
        if (n > 0) fib[0] = 0;
        if (n > 1) fib[1] = 1;
        for (int i = 2; i < n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }

    @Override
    public int Sumatoria() throws RemoteException {
        int suma = 0;
        for (int i = 1; i <= n; i++) {
            suma += i;
        }
        return suma;
    }
}


