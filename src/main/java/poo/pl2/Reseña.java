package poo.pl2;

import java.time.LocalDate;

public class Reseña implements java.io.Serializable{
    private String comentario;
    private int calificacion;
    private Particular particular;
    private Inmueble inmueble;
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
        return particular;
    }

    public void setParticular(Particular particular) {
        this.particular = particular;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }
    public LocalDate getFechaReseña() {
        return fechaReseña;
    }
    public void setFechaReseña(LocalDate fechaReseña) {
        this.fechaReseña = fechaReseña;
    }
    public Reseña(Particular particular, Inmueble inmueble, Reserva reserva, LocalDate fechaReseña, int calificacion, String comentario) {
        this.comentario = comentario;
        this.calificacion = Math.max(1, Math.min(5, calificacion));
        this.particular = particular;
        this.inmueble = inmueble;
        this.reserva = reserva;
        this.reserva.setYaReseñado(true);
        this.fechaReseña = fechaReseña;
    }
}
