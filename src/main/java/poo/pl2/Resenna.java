package poo.pl2;

import java.time.LocalDate;

public class Resenna implements java.io.Serializable{
    private String comentario;
    private int calificacion;

    private Reserva reserva;
    private LocalDate fechaResenna;
    
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public Particular getParticular() {
        return reserva.getParticular();
    }

    public void setParticular(Particular particular) {
        this.reserva.setParticular(particular);
    }

    public Inmueble getInmueble() {
        return reserva.getInmueble();
    }

    public void setInmueble(Inmueble inmueble) {
        this.reserva.setInmueble(inmueble);
    }
    public LocalDate getFechaResenna() {
        return fechaResenna;
    }
    public void setFechaResenna(LocalDate fechaResenna) {
        this.fechaResenna = fechaResenna;
    }
    /**
     * Constructor para la clase Resenna.
     * Inicializa una nueva resenna con los detalles proporcionados.
     * La calificacion se limita a un minimo de 1 y un maximo de 5.
     * Lanza una excepcion si la reserva ya ha sido resennada o si la reserva no ha finalizado.
     *
     * @param reserva La reserva asociada a la resenna.
     * @param calificacion La calificacion dada al inmueble.
     * @param comentario El comentario sobre el inmueble.
     * @throws IllegalArgumentException Si la reserva ya ha sido resennada o si la reserva no ha finalizado aun.
     */
    public Resenna(Reserva reserva, int calificacion, String comentario) {
        this.comentario = comentario;
        this.calificacion = Math.max(1, Math.min(5, calificacion));
        this.reserva = reserva;
        if (this.reserva.isYaResennado())
            throw new IllegalArgumentException("No se puede resennar una reserva que ya ha sido resennada");
        this.reserva.setYaResennado(true);
        if (LocalDate.now().isBefore(reserva.getFechaSalida()))
            throw new IllegalArgumentException("No se puede resennar una reserva que no ha finalizado");
        this.fechaResenna = LocalDate.now();
    }
}
