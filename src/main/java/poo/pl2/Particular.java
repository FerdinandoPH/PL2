package poo.pl2;

import java.util.ArrayList;
import java.util.Collections;

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
    public ArrayList<Reserva> getReservas(){
        ArrayList<Reserva> reservas = new ArrayList<Reserva>();
        for (Reserva r: Reserva.getReservas()){
            if (r.getParticular().equals(this)){
                reservas.add(r);
            }
        }
        Collections.sort(reservas, (Reserva r1, Reserva r2) -> r1.getFechaEntrada().compareTo(r2.getFechaEntrada()));
        return reservas;
    }

    @Override
    public String toString() {
        return "Particular: ["+super.toString()+", esVip=" + vip + ", tarjeta=" + tarjeta.toString() + "]";
    }
    
}//end Particular