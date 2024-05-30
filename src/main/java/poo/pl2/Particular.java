package poo.pl2;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author perez
 * @version 1.0
 * 
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
    /**
     * Obtiene una lista de todas las reservas hechas por este particular.
     * Este metodo recorre todas las reservas, y si la reserva fue hecha por este particular, la annade a la lista.
     * La lista de reservas se ordena por la fecha de entrada.
     *
     * @return Una lista de todas las reservas hechas por este particular.
     */
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
    /**
     * Constructor para la clase Particular.
     * Inicializa un nuevo particular con los detalles proporcionados.
     *
     * @param correo El correo del particular.
     * @param claveString La contrasenna del particular.
     * @param dni El DNI del particular.
     * @param nombre El nombre del particular.
     * @param telefono El telefono del particular.
     * @param esVip Indica si el particular es VIP.
     * @param tarjeta La tarjeta de credito del particular.
     */
    public Particular(String correo, String claveString, String dni, String nombre, String telefono, boolean esVip, Tarjeta tarjeta){
        super(correo, claveString, dni, nombre, telefono);
        this.vip = esVip;
        this.tarjeta = tarjeta;
    }

    @Override
    public String toString() {
        return "Particular: ["+super.toString()+", esVip=" + vip + ", tarjeta=" + tarjeta.toString() + "]";
    }
    
}//end Particular