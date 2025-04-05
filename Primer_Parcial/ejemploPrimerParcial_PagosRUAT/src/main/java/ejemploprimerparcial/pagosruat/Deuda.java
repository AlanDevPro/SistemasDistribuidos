package ejemploprimerparcial.pagosruat;

import java.io.Serializable;

public class Deuda implements Serializable {
    private String ci;
    private int anio;
    private String impuesto;
    private double monto;

    public Deuda(String ci, int anio, String impuesto, double monto) {
        this.ci = ci;
        this.anio = anio;
        this.impuesto = impuesto;
        this.monto = monto;
    }

    // Getters
    public String getCi() { return ci; }
    public int getAnio() { return anio; }
    public String getImpuesto() { return impuesto; }
    public double getMonto() { return monto; }

    // Setters (opcional)
    public void setCi(String ci) { this.ci = ci; }
    public void setAnio(int anio) { this.anio = anio; }
    public void setImpuesto(String impuesto) { this.impuesto = impuesto; }
    public void setMonto(double monto) { this.monto = monto; }

    @Override
    public String toString() {
        return anio + ", " + impuesto + ", " + monto;
    }
}
