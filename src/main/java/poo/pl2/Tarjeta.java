package poo.pl2;

import java.time.LocalDate;


/**
 * @author perez
 * @version 1.0
 * @created 07-may.-2024 17:29:06
 */
public class Tarjeta implements java.io.Serializable{

    private LocalDate fechaCaducidad;
    private String nombreTitular;
    private long numero;
    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }
    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }
    public String getNombreTitular() {
        return nombreTitular;
    }
    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }
    public long getNumero() {
        return numero;
    }
    public void setNumero(long numero) {
        this.numero = numero;
    }
    public Tarjeta(LocalDate fechaCaducidad, String nombreTitular, long numero) {
        this.fechaCaducidad = fechaCaducidad;
        this.nombreTitular = nombreTitular;
        this.numero = numero;
    }
    @Override
    public String toString() {
        return "[fechaCaducidad=" + fechaCaducidad + ", nombreTitular=" + nombreTitular + ", numero=" + numero
                + "]";
    }
    

    
}//end Tarjeta