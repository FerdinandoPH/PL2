package poo.pl2;

public class Reseña implements java.io.Serializable{
    private String comentario;
    private int calificacion;
    private Particular particular;
    private Inmueble inmueble;
    
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

    public Reseña(Particular particular, Inmueble inmueble, String comentario, int calificacion) {
        this.comentario = comentario;
        this.calificacion = Math.max(1, Math.min(5, calificacion));
        this.particular = particular;
        this.inmueble = inmueble;
    }
    
}
