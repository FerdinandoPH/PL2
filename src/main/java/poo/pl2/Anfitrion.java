package poo.pl2;
import java.time.LocalDate;

/**
 * @author perez
 * @version 1.0
 * @created 07-may.-2024 17:29:06
 */
public class Anfitrion extends Cliente {

    private boolean superAnfitrion;
    private LocalDate fechaRegistro;
    
    public boolean isSuperAnfitrion() {
        return superAnfitrion;
    }

    public void setSuperAnfitrion(boolean esSuperAnfitiron) {
        this.superAnfitrion = esSuperAnfitiron;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Anfitrion(String correo, String claveString, String dni, String nombre, String telefono){
        super(correo, claveString, dni, nombre, telefono);
        this.superAnfitrion = false;
        this.fechaRegistro = LocalDate.now();
    }

    public void consultarReservas(){

    }

    public void crearInmueble(){

    }

    @Override
    public String toString() {
        return "Anfitrion: ["+super.toString()+", esSuperAnfitiron=" + superAnfitrion + ", fechaRegistro=" + fechaRegistro + "]";
    }
    
}//end Anfitrion