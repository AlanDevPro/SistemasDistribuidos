/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examenpacial;

import java.io.Serializable;

/**
 *
 * @author Dell
 */
public class Cuenta implements Serializable {
    Banco banco;
    String nroCuenta;
    String ci;
    String nombres;
    String apellidos;
    double saldo;

    public Cuenta(Banco banco, String nroCuenta, String ci, String nombres, String apellidos, double saldo) {
        this.banco = banco;
        this.nroCuenta = nroCuenta;
        this.ci = ci;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.saldo = saldo;
    }

    public Banco getBanco() {
        return banco;
    }

    public String getNroCuenta() {
        return nroCuenta;
    }

    public String getCi() {
        return ci;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public void setNroCuenta(String nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
   

    
    
}
