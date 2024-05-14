package poo.pl2;


/**
 * @author perez
 * @version 1.0
 * @created 07-may.-2024 17:29:06
 */
public class Inmueble implements java.io.Serializable{

    private int baños;
    private double calificacion;
    private int camas;
    private int fotografia;
    private int habitaciones;
    private int huespedesMaximos;
    private double precioPorNoche;
    private String servicios;
    private enum tipoPropiedad {APARTAMENTO, CASA}
    private String titulo;
    private int vecesReservado;
    private Anfitrion dueño;

    public Inmueble(){

    }
}//end Inmueble