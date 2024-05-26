package poo.pl2;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author perez
 * @version 1.0
 * @created 07-may.-2024 17:29:06
 */
public class Reserva implements java.io.Serializable{
    private LocalDate fechaReserva;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private double importe;
    private Inmueble inmueble;
    private Particular particular;
    private int id;
    private boolean yaReseñada=false;
    //region gettersYsetters

        public LocalDate getFechaReserva() {
            return fechaReserva;
        }



        public void setFechaReserva(LocalDate fechaReserva) {
            this.fechaReserva = fechaReserva;
        }



        public LocalDate getFechaEntrada() {
            return fechaEntrada;
        }



        public void setFechaEntrada(LocalDate fechaEntrada) {
            this.fechaEntrada = fechaEntrada;
        }



        public LocalDate getFechaSalida() {
            return fechaSalida;
        }



        public void setFechaSalida(LocalDate fechaSalida) {
            this.fechaSalida = fechaSalida;
        }



        public double getImporte() {
            return importe;
        }



        public void setImporte(double importe) {
            this.importe = importe;
        }



        public Inmueble getInmueble() {
            return inmueble;
        }



        public void setInmueble(Inmueble inmueble) {
            this.inmueble = inmueble;
        }



        public Particular getParticular() {
            return particular;
        }



        public void setParticular(Particular particular) {
            this.particular = particular;
        }



        public int getId() {
            return id;
        }



        public void setId(int id) {
            this.id = id;
        }
    //endregion


    public Reserva(LocalDate fechaReserva, LocalDate fechaEntrada, LocalDate fechaSalida,
            Inmueble inmueble, Particular particular, int id) {
        this.fechaReserva = fechaReserva;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.importe = ChronoUnit.DAYS.between(fechaEntrada, fechaSalida)*inmueble.getPrecioPorNoche();
        this.inmueble = inmueble;
        this.particular = particular;
        this.id = id;
    }
    public static ArrayList<Reserva> getReservas(){
        ArrayList<Reserva> reservas=ListManager.getReservas();
        Collections.sort(reservas, new Comparator<Reserva>(){
            public int compare(Reserva r1, Reserva r2){
                return r1.getFechaReserva().compareTo(r2.getFechaReserva());
            }
        });
        return reservas;
    }
    public static void añadirReserva(LocalDate fechaReserva, LocalDate fechaEntrada, LocalDate fechaSalida, Inmueble inmueble,
            Particular particular){
        if (validarReserva(fechaReserva, fechaEntrada, fechaSalida, inmueble, particular).isEmpty())
                throw new IllegalArgumentException(validarReserva(fechaReserva, fechaEntrada, fechaSalida, inmueble, particular));
        ListManager.getReservas().add(new Reserva(fechaReserva, fechaEntrada, fechaSalida, inmueble, particular, ListManager.getReservas().size()));
        inmueble.setVecesReservado(inmueble.getVecesReservado()+1);
    }
    public static void borrarReserva (int id){
        ListManager.getReservas().remove(id);
    }
    public static String validarReserva(LocalDate fechaReserva, LocalDate fechaEntrada, LocalDate fechaSalida, Inmueble inmueble,
    Particular particular){
        String errores="";
        if(fechaReserva.isAfter(fechaEntrada) || fechaReserva.isAfter(fechaSalida))
            errores+="La fecha de reserva no puede ser posterior a la fecha de entrada o salida\n";
        if(fechaEntrada.isAfter(fechaSalida))
            errores+="La fecha de entrada no puede ser posterior a la fecha de salida\n";
        if (fechaReserva.isBefore(LocalDate.now().plusDays(1)) || fechaEntrada.isBefore(LocalDate.now().plusDays(1)) || fechaSalida.isBefore(LocalDate.now().plusDays(1)))
            errores+="No se puede reservar antes de mañana\n";
        if (ChronoUnit.YEARS.between(fechaReserva, fechaEntrada)>0 || ChronoUnit.YEARS.between(fechaReserva, fechaSalida)>0)
            errores+="No se pueden reservar inmuebles a más de un año vista\n";
        if (ChronoUnit.MONTHS.between(fechaEntrada, fechaSalida)>2)
            errores+="No se pueden reservar inmuebles por más de dos meses\n";
        for (Reserva r: Reserva.getReservas()){
            if (r.getParticular().equals(particular)){
                if (!((fechaSalida.isAfter(r.getFechaSalida())&& (fechaEntrada.isAfter(r.getFechaSalida())||fechaEntrada.equals(r.getFechaSalida())))||((fechaSalida.isBefore(r.getFechaEntrada())||fechaSalida.equals(r.getFechaEntrada()))&& fechaEntrada.isBefore(r.getFechaEntrada()))))
                    errores+="Ya tienes una reserva en esas fechas\n";
            }
        }
        for (Reserva r:ListManager.getReservas()){
            if (r.getInmueble().equals(inmueble)&&!((fechaSalida.isAfter(r.getFechaSalida())&& (fechaEntrada.isAfter(r.getFechaSalida())||fechaEntrada.equals(r.getFechaSalida())))||((fechaSalida.isBefore(r.getFechaEntrada())||fechaSalida.equals(r.getFechaEntrada()))&& fechaEntrada.isBefore(r.getFechaEntrada()))))
                errores+="El inmueble ya está reservado en esas fechas\n";
        }
        return errores;
    }


    public void imprimirFactura(){

    }
}//end Reserva