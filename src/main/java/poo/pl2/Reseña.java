package poo.pl2;

public class Reseña implements java.io.Serializable{
    private String comentario;
    private int calificacion;
    private Cliente cliente;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public Reseña(Cliente cliente, Inmueble inmueble, String comentario, int calificacion) {
        this.comentario = comentario;
        this.calificacion = Math.max(1, Math.min(5, calificacion));
        this.cliente = cliente;
        this.inmueble = inmueble;
    }
    
}
