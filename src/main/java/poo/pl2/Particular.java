package poo.pl2;


/**
 * @author perez
 * @version 1.0
 * @created 07-may.-2024 17:29:06
 */
public class Particular extends Cliente {

    private boolean esVip;
    private Tarjeta tarjeta;

    public Particular(String correo, String claveString, String dni, String nombre, String telefono, Tarjeta tarjeta){
        super(correo, claveString, dni, nombre, telefono);
        this.esVip = false;
        this.tarjeta = tarjeta;
    }

    public void buscarInmuebles(){

    }

    public void consultarReservas(){

    }

    public void reseñarInmuebles(){

    }

    public void reservarInmuebles(){

    }
}//end Particular