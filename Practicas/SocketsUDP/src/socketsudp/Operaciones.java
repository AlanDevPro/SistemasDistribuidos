/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package socketsudp;

/**
 *
 * @author TODO LAPTOP
 */
public class Operaciones {
    public int n;
    
    

    public void setN(int n) {
        this.n = n;
    }

    public int getN() {
        return n;
    }

    public int fibonacci() {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        int a = 0, b = 1, c = 0;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    public int factorial() {
        if (n < 0) throw new IllegalArgumentException("n debe ser mayor o igual a 0");
        int resultado = 1;
        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }

    public int sumatoria() {
        return (n * (n + 1)) / 2;
    }    
}
    
