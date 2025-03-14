/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab1_pagosruat;

/**
 *
 * @author TODO LAPTOP
 */
import java.io.Serializable;

public class Deuda implements Serializable {
    private String CI;
    private int anio;
    private String impuesto;
    private double monto;

    public Deuda(String CI, int anio, String impuesto, double monto) {
        this.CI = CI;
        this.anio = anio;
        this.impuesto = impuesto;
        this.monto = monto;
    }

    public String getCI() { return CI; }
    public int getAnio() { return anio; }
    public String getImpuesto() { return impuesto; }
    public double getMonto() { return monto; }
}

