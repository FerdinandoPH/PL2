package poo.pl2;


/**
 * @author perez
 * @version 1.0
 * @created 07-may.-2024 17:29:06
 */
public class Particular extends Cliente {

    private boolean vip;
    private Tarjeta tarjeta;
    
    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean esVip) {
        this.vip = esVip;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

    public Particular(String correo, String claveString, String dni, String nombre, String telefono, boolean esVip, Tarjeta tarjeta){
        super(correo, claveString, dni, nombre, telefono);
        this.vip = esVip;
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

    @Override
    public String toString() {
        return "Particular: ["+super.toString()+", esVip=" + vip + ", tarjeta=" + tarjeta.toString() + "]";
    }
    
}//end Particular