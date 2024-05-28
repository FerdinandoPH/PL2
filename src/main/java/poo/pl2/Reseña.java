package poo.pl2;

import java.time.LocalDate;

public class Reseña implements java.io.Serializable{
    private String comentario;
    private int calificacion;

    private Reserva reserva;
    private LocalDate fechaReseña;
    
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
    public LocalDate getFechaReseña() {
        return fechaReseña;
    }
    public void setFechaReseña(LocalDate fechaReseña) {
        this.fechaReseña = fechaReseña;
    }
    /**
     * Constructor para la clase Reseña.
     * Inicializa una nueva reseña con los detalles proporcionados.
     * La calificación se limita a un mínimo de 1 y un máximo de 5.
     * Lanza una excepción si la reserva ya ha sido reseñada o si la reserva no ha finalizado.
     *
     * @param reserva La reserva asociada a la reseña.
     * @param calificacion La calificación dada al inmueble.
     * @param comentario El comentario sobre el inmueble.
     * @throws IllegalArgumentException Si la reserva ya ha sido reseñada o si la reserva no ha finalizado aún.
     */
    public Reseña(Reserva reserva, int calificacion, String comentario) {
        this.comentario = comentario;
        this.calificacion = Math.max(1, Math.min(5, calificacion));
        this.reserva = reserva;
        if (this.reserva.isYaReseñado())
            throw new IllegalArgumentException("No se puede reseñar una reserva que ya ha sido reseñada");
        this.reserva.setYaReseñado(true);
        if (LocalDate.now().isBefore(reserva.getFechaSalida()))
            throw new IllegalArgumentException("No se puede reseñar una reserva que no ha finalizado");
        this.fechaReseña = LocalDate.now();
    }
}
