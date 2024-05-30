package poo.pl2;

import java.time.LocalDate;


/**
 * @author perez
 * @version 1.0
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
    /**
     * Crea una nueva instancia de la clase Tarjeta.
     * Valida la informacion de la tarjeta antes de crear la instancia.
     * Lanza una excepcion si la informacion de la tarjeta no es valida.
     *
     * @param fechaCaducidad La fecha de caducidad de la tarjeta.
     * @param nombreTitular El nombre del titular de la tarjeta.
     * @param numero El numero de la tarjeta.
     * @throws IllegalArgumentException Si la informacion de la tarjeta no es valida.
     */
    public Tarjeta(LocalDate fechaCaducidad, String nombreTitular, long numero) {
        String errores=validarInformacion(nombreTitular, String.valueOf(numero), fechaCaducidad.getMonthValue(), fechaCaducidad.getYear());
        if (!errores.isEmpty())
            throw new IllegalArgumentException(errores);
        this.fechaCaducidad = fechaCaducidad;
        this.nombreTitular = nombreTitular;
        this.numero = numero;
    }
    /**
     * Valida la informacion de una tarjeta.
     * Comprueba si los campos no estan vacios, si el numero de la tarjeta es valido y si la fecha de caducidad es valida.
     *
     * @param nombreTarjeta El nombre en la tarjeta.
     * @param numeroTarjeta El numero de la tarjeta.
     * @param mes El mes de expiracion de la tarjeta.
     * @param anno El anno de expiracion de la tarjeta.
     * @return Una cadena con los errores si la informacion no es valida, o una cadena vacia si lo es.
     */
    public static String validarInformacion(String nombreTarjeta, String numeroTarjeta,int mes, int anno){
    String errores="";
        if (numeroTarjeta.isEmpty() || nombreTarjeta.isEmpty())
        errores+="Es necesario rellenar todos los campos de la tarjeta\n";
        if (!numeroTarjeta.matches("[0-9]{16}"))
            errores+="Numero de tarjeta invalido\n";
        if (anno<LocalDate.now().getYear() || (anno==LocalDate.now().getYear() && mes<LocalDate.now().getMonthValue() || mes>12 || mes<1))
            errores+="Fecha de caducidad invalida\n";
        return errores;
    }
    @Override
    public String toString() {
        return "[fechaCaducidad=" + fechaCaducidad + ", nombreTitular=" + nombreTitular + ", numero=" + numero
                + "]";
    }
    

    
}//end Tarjeta