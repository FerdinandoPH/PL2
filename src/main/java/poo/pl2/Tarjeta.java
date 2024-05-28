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
     * Valida la información de la tarjeta antes de crear la instancia.
     * Lanza una excepción si la información de la tarjeta no es válida.
     *
     * @param fechaCaducidad La fecha de caducidad de la tarjeta.
     * @param nombreTitular El nombre del titular de la tarjeta.
     * @param numero El número de la tarjeta.
     * @throws IllegalArgumentException Si la información de la tarjeta no es válida.
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
     * Valida la información de una tarjeta.
     * Comprueba si los campos no están vacíos, si el número de la tarjeta es válido y si la fecha de caducidad es válida.
     *
     * @param nombreTarjeta El nombre en la tarjeta.
     * @param numeroTarjeta El número de la tarjeta.
     * @param mes El mes de expiración de la tarjeta.
     * @param año El año de expiración de la tarjeta.
     * @return Una cadena con los errores si la información no es válida, o una cadena vacía si lo es.
     */
    public static String validarInformacion(String nombreTarjeta, String numeroTarjeta,int mes, int año){
    String errores="";
        if (numeroTarjeta.isEmpty() || nombreTarjeta.isEmpty())
        errores+="Es necesario rellenar todos los campos de la tarjeta\n";
        if (!numeroTarjeta.matches("[0-9]{16}"))
            errores+="Número de tarjeta inválido\n";
        if (año<LocalDate.now().getYear() || (año==LocalDate.now().getYear() && mes<LocalDate.now().getMonthValue() || mes>12 || mes<1))
            errores+="Fecha de caducidad inválida\n";
        return errores;
    }
    @Override
    public String toString() {
        return "[fechaCaducidad=" + fechaCaducidad + ", nombreTitular=" + nombreTitular + ", numero=" + numero
                + "]";
    }
    

    
}//end Tarjeta